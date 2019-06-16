package com.elon.feign.server.rest;

import com.elon.feign.server.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 用于测试feign的rest接口定义。
 */
@RestController
@RequestMapping("/v1/feign-server")
public class FeignServerController {

    /**
     * 根据ID查询用户
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/user/{id}")
    public User queryUserById(@PathVariable("id") int id) {
        System.out.println("id:" + id);

        User user = new User();
        user.setId(id);
        return user;
    }

    /**
     * 根据名称查询用户
     * @param name 名称
     * @return 用户信息
     */
    @GetMapping("/user/query-by-name")
    public User queryUserByName(@RequestParam("name") String name) {
        System.out.println("name:" + name);
        User user = new User();
        user.setName(name);
        return user;
    }

    /**
     * 修改用户信息
     * @param name 姓名
     * @param age 年龄
     * @param id 用户ID
     * @return 用户信息
     */
    @PostMapping("/user/update-user/{id}")
    public User updateUserInfo(@RequestHeader("name") String name, @RequestHeader("age") int age,
                               @PathVariable("id") int id){

        // header 是8859编码，不做转码的话，中文会乱码
        try {
            name = new String(name.getBytes("ISO-8859-1"), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("id:" + id + "|name:" + name + "|age:" + age);
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return 处理结果
     */
    @PutMapping(value = "/user")
    public boolean addUser(@RequestBody User user){
        System.out.println("user:" + user);
        return true;
    }

    /**
     * 删除用户。
     * @param userId 用户ID
     * @return 处理结果
     */
    @DeleteMapping("/user/{userId}")
    public boolean deleteUser(@PathVariable("userId") int userId) {
        System.out.println("Delete user. userId:" + userId);
        return true;
    }
}
