package com.elon.feign.client;

import com.elon.feign.client.model.User;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeignClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class);
        System.out.println("Start up Feign client success!");

        ProxyInterface proxyInterface = buildInterfaceInstance(ProxyInterface.class,
                "http://localhost:10001/feignserver/");

        User user = proxyInterface.queryUserById(123);
        System.out.println("GET请求&&path参数：" + user);

        User user2 = proxyInterface.queryUserByName("张三");
        System.out.println("GET请求&&query参数：" + user2);

        User user3 = proxyInterface.updateUserInfo("李四", 25, 100);
        System.out.println("POST请求&&header参数：" + user3);

        User user4 = new User();
        user4.setId(10001);
        user4.setName("王五");
        user4.setAge(30);
        boolean result = proxyInterface.addUser(user4);
        System.out.println("PUT请求&&Body参数：" + result);

        boolean result2 = proxyInterface.deleteUser(9999);
        System.out.println("DELETE请求&&Path参数：" + result2);
    }

    private static <T> T buildInterfaceInstance(Class<T> type, String url) {
        return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                .target(type, url);
    }
}
