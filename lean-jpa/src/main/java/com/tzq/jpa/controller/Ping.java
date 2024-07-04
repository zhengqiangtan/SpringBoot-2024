package com.tzq.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class Ping {
    @RequestMapping("ping")
    public String ping() {
        return "pong ping ping sdfsdfs 1231321313";
    }
    @RequestMapping("hi")
    public String test() {
        return "hello spring";
    }

    @RequestMapping("hh")
    public String test2() {
        return "hello tanzheg qiabg";
    }
}

