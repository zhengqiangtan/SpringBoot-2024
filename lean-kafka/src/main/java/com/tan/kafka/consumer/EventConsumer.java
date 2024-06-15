package com.tan.kafka.consumer;


import com.tan.kafka.model.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {

    @KafkaListener(topics = "events", groupId = "events-group-1")
    public void consume(Event event) {
        System.out.println("Consumed event: " + event);
    }
}

