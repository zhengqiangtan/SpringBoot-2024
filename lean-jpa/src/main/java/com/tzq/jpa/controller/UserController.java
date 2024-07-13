package com.tzq.jpa.controller;

import com.tzq.jpa.dao.UserRepository;
import com.tzq.jpa.entity.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/7/8 22:39
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("findUserByName")
    public ResponseEntity<User> findUserByName() {

        User user = userRepository.findByUserName("zhangsan");
        return ResponseEntity.ok(user);
    }
}

