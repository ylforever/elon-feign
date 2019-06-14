package com.elon.feign.client;

import com.elon.feign.client.model.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 客户端代理接口
 */
public interface ProxyInterface {

    @RequestLine("GET /v1/feign-server/user/{id}")
    User queryUserById(@Param("id") int id);

    @RequestLine("GET /v1/feign-server/user/query-by-name?name={name}")
    User queryUserByName(@Param("name") String name);

    @Headers({"name: {name}", "age: {age}"})
    @RequestLine("POST /v1/feign-server/user/update-user/{id}")
    User updateUserInfo(@Param("name") String name, @Param("age") int age, @Param("id") int id);

    @Headers("content-type: application/json")
    @RequestLine("PUT /v1/feign-server/user")
    boolean addUser(User user);
}
