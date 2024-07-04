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
//     * 写法 1：指定具体消费者 topic,消费者组（这里消费者组可以覆盖消费者工厂中配置的消费者组）
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
//     * 写法 2：使用 SpEL（Spring Expression Language）进行动态属性解析
//     *  Spring 使用 SpEL 来解析注解中的表达式。在 Spring Kafka 中，
//     *  __listener 提供了一种方式来引用当前监听器 bean 的上下文，从而可以动态地访问和配置监听器的属性。
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
//     * 写法 3：指定不同的消费者组
//     *
//     * @param event
//     */
//    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-3}")
//    public void consume3(Event event) {
//        System.out.println("Consumer group-3 consumed event: " + event);
//    }


    /**
     * 写法 4：
     * 1. 设置 ID 方便启动、和停止消费者
     * 2.可以指定 autoStartup 属性为 false，手动启动消费者,默认情况下是 true
     *
     * @param event
     */
    @KafkaListener(id = "eventListener", topics = "events_test",
            groupId = "default", autoStartup = "true", concurrency = "2")
    public void consume5(Event event) {
        System.out.println("[event-listener] consumed event: " + event);
    }
}

