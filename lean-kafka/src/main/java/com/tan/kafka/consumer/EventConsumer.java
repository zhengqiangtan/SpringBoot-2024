package com.tan.kafka.consumer;


import com.tan.kafka.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * 同主题配置不同的消费组测试：每个消费者都可以获取到相同的消息
 * 同主题一个分区只能被多个相同消费组中的一个消费者消费
 */
@Service
public class EventConsumer {
    @Value("${kafka.topic}")
    public String topic;

    @Value("${kafka.group-2}")
    public String groupId;

//    /**
//     * 写法 1：消费者组 1
//     *
//     * @param event
//     */
//    @KafkaListener(topics = "events", groupId = "events-group-1")
//    public void consume(Event event) {
//        System.out.println("Consumed event: " + event);
//    }
//
//
//    /**
//     * 写法 1：消费者组 2
//     *
//     * @param message
//     */
//    @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.groupId}")
//    public void consume2(Event message) {
//        System.out.println("Consumed message: " + message);
//    }
//
//
//    /**
//     * 写法 3：消费者组 3
//     *
//     * @param event
//     */
//    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-3}")
//    public void consume3(Event event) {
//        System.out.println("Consumer group-3 consumed event: " + event);
//    }
//
//
//    /**
//     * 写法 4：消费者消费数据并写入到 MySQL 中
//     *
//     * @param event
//     */
//    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-4")
//    public void consume4(Event event) {
//        System.out.println("Consumer group-3 consumed event: " + event);
//    }


    /**
     * 写法 5：消费者暂停恢复测试
     *
     * @param event
     */
    @KafkaListener(id = "eventListener", topics = "${kafka.topic}", groupId = "${kafka.group-5")
    public void consume5(Event event) {
        System.out.println("[event-listener] group-5 consumed event: " + event);
    }
}

