package com.tan.kafka.config;


import com.tan.kafka.model.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.support.TransactionSynchronization;

/**
 * @Description kafka事务同步控制器
 * @Author zhengqiang.tan
 * @Date 2024/6/15 23:05
 */
public class KafkaTransactionSynchronization implements TransactionSynchronization {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;
    private final Event event;

    public KafkaTransactionSynchronization(KafkaTemplate<String, Object> kafkaTemplate, String topic, Event event) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.event = event;
    }

    @Override
    public void afterCommit() {
        kafkaTemplate.send(topic, event);
        System.out.println("Sent message=[" + event + "] to topic=[" + topic + "]");
    }

    @Override
    public void beforeCommit(boolean readOnly) {
    }

    @Override
    public void beforeCompletion() {
    }

    @Override
    public void afterCompletion(int status) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void suspend() {
    }

    @Override
    public void resume() {
    }
}
