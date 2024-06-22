package com.tan.annotation.controller;

import com.tan.annotation.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time 21:03
 * @discription
 **/
@RestController
public class UserController {

    /**
     * 定义一个创建用户的接口
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getCreateTime().toString());
        // 将 user 再以 Json 的形式返回
        return user;
    }
}