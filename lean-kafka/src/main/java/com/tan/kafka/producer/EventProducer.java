package com.tan.kafka.producer;


import com.tan.kafka.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private static final String TOPIC = "events";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Event event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
