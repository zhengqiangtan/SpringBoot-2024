package com.tan.kafka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tan.kafka.mapper")
@SpringBootApplication(scanBasePackages = {"com.tan.kafka"})
public class LeanKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeanKafkaApplication.class, args);
    }
}
