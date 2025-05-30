package com.tan.annotation.controller;

import com.tan.annotation.entity.UserEntity;
import com.tan.annotation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/// TODO ..  https://freegeektime.com/100047701/227917/

@Slf4j
@RestController
@RequestMapping("metricstest")
public class MetricsController {
    @Autowired
    private UserService userService;
    @GetMapping("transaction")
    public int transaction(@RequestParam("name") String name) {
        try {
            userService.createUser(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed because {}", ex.getMessage());
        }
        return userService.getUserCount(name);
    }
}