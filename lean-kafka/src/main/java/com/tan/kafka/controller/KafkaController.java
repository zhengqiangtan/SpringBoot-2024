package com.tan.kafka.controller;

import com.tan.kafka.service.KafkaListenerControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description 控制消费者是否启动消费或者暂停
 * @Author zhengqiang.tan
 * @Date 2024/6/16 09:39
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaListenerControlService kafkaListenerControlService;

    @PostMapping("/pause")
    public String pause() {
        kafkaListenerControlService.pauseListener();
        return "Paused";
    }

    @PostMapping("/resume")
    public String resume() {
        kafkaListenerControlService.resumeListener();
        return "Resumed";
    }
}
