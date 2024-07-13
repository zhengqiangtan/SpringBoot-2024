package com.tan.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = "com.tan.redis")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
