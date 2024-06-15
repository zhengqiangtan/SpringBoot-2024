package com.tan.kafka;

import com.tan.kafka.model.Event;
import com.tan.kafka.producer.EventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@SpringBootTest
public class KafkaMsgSendTest {


    @Autowired
    private EventProducer eventProducer;

    @Test
    public void sendMsg() throws Exception {
        eventProducer.sendMessage("events", new Event(1L, "110", "test msg " + new Random().nextInt(10000)));

    }

}

