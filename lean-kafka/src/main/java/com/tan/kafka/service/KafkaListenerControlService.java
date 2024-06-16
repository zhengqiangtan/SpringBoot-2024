package com.tan.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Kafka监听器控制服务
 */
@Service
@Slf4j
public class KafkaListenerControlService {

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @PostConstruct
    public void checkListeners() {
        log.info("KafkaListenerControlService.checkListeners");
        for (String listenerId : kafkaListenerEndpointRegistry.getListenerContainerIds()) {
            MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);
            if (container != null) {
                System.out.println("Registered Kafka listener: " + listenerId);
            } else {
                System.out.println("No Kafka listener found with id: " + listenerId);
            }
        }
    }

    /**
     * 暂停指定名称的Kafka监听器。
     * <p>
     * 本方法旨在提供一种方式来暂停应用程序中特定的Kafka监听器。这可能在需要临时停止处理新消息，
     * 例如进行系统维护或升级时的场景下非常有用。
     * 这里使用 eventListener 仅用于测试。
     *
     * @see KafkaListenerEndpointRegistry#getListenerContainer(String) 用于获取指定名称的监听器容器
     * @see MessageListenerContainer#pause() 用于暂停监听器容器，使其停止接收和处理新消息
     */
    public void pauseListener() {
        // 通过监听器端点注册表获取名为"eventListener"的监听器容器
        MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("eventListener");
        // 检查容器是否为空，如果非空，则调用其pause方法暂停监听器
        if (container != null) {
            container.pause();
            log.info("Paused Kafka listener,id = {}", container.getListenerId());
        }
    }

    /**
     * 恢复指定名称的Kafka监听器。
     * 此方法用于暂停后的监听器重新启动，以便它可以继续处理消息。
     * 它首先从注册表中获取名为"eventListener"的监听器容器，
     * 然后检查容器是否为空。如果容器存在，则调用其resume方法来恢复监听器的运行。
     * 最后，记录一条信息表明监听器已恢复。
     *
     * @see KafkaListenerEndpointRegistry#getListenerContainer(String)
     * @see MessageListenerContainer#resume()
     */
    public void resumeListener() {
        // 从注册表中获取名为"eventListener"的监听器容器
        MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("eventListener");
        // 检查容器是否存在，如果存在，则恢复容器的运行
        if (container != null) {
            container.resume();
            // 记录恢复操作的信息，包括监听器的ID
            log.info("Resumed Kafka listener,id = {}", container.getListenerId());
        }
    }
}
