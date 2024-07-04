package com.tzq.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/7/4 21:57
 */
@SpringBootApplication(scanBasePackages = "com.tzq.jpa")
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}

