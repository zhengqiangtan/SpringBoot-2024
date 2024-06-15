package com.tan.kafka.producer;


import com.tan.kafka.config.KafkaTransactionSynchronization;
import com.tan.kafka.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class EventProducer {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Event event) {
        kafkaTemplate.send(topic, event);
    }


    /**
     * 在事务中发送消息,确保这些消息在事务提交后发送
     * @param topic
     * @param event
     */
    @Transactional
    public void sendEvent(String topic, Event event) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new KafkaTransactionSynchronization(kafkaTemplate, topic, event));
        } else {
            // 在非事务上下文中直接发送消息
            kafkaTemplate.send(topic, event);
        }
    }
}
