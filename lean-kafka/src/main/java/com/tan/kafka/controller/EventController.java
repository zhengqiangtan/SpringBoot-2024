package com.tan.kafka.controller;


import com.tan.kafka.model.Event;
import com.tan.kafka.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 测试消息发送
 * curl -X POST http://localhost:8080/api/kafka/publish \
 * -H "Content-Type: application/json" \
 * -d '{"id": "1", "message": "Hello Kafka"}'
 */
@RestController
@RequestMapping("/api/kafka")
public class EventController {

    @Autowired
    private EventProducer eventProducer;

    @Value("${kafka.topic}")
    private String topic;

    @PostMapping("/publish")
    public String sendMessage(@RequestBody Event event) {
        eventProducer.sendMessage(topic, event);
        return "Message sent to Kafka topic : " + event;
    }


    /**
     * 发送自定义事件: 以事务的方式确保消息发送的可靠性
     * @param event
     * @return
     */
    @PostMapping("/publish-event")
    public String sendMessage2(@RequestBody Event event) {
        eventProducer.sendEvent(topic, event);
        return "Sent Event : " + event;
    }
}
