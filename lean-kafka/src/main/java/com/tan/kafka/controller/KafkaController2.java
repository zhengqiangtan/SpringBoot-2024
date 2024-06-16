package com.tan.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class KafkaController2 {
    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    /**
     * 开启监听,写法 1 存在线程安全问题
     */
    @Deprecated
    @GetMapping("/start-1")
    public void start1() {
        // 判断监听容器是否启动，未启动则将其启动
        if (!Objects.requireNonNull(registry.getListenerContainer("eventListener")).isRunning()) {
            log.info("start eventListener ");
            Objects.requireNonNull(registry.getListenerContainer("eventListener")).start();
        }
        // 将其恢复
        Objects.requireNonNull(registry.getListenerContainer("eventListener")).resume();
        log.info("resume over ");
    }

    /**
     * 关闭监听
     */
    @GetMapping("/pause-1")
    public void pause1() {
        // 暂停监听
        Objects.requireNonNull(registry.getListenerContainer("eventListener")).pause();
        log.info("pause eventListener is done");
    }


    /**
     * 开启消费者监听，线程安全版本
     * @param listenerName
     */
    @GetMapping("/start/{listenerName}")
    public void start(@PathVariable String listenerName) {
        try {
            lock.lock();
            if (!isListenerRunning(listenerName)) {
                log.info("start {} ... ", listenerName);
                startListener(listenerName);
            }
            resumeListener(listenerName);
            log.info("resume {} is done. ", listenerName);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 暂停指定名称的监听器。这有助于提高代码的灵活性和可维护性，
     * 允许在不修改代码的情况下暂停不同的监听器。
     *
     * @param listenerName 监听器的名称。
     */
    @GetMapping("/pause/{listenerName}")
    public void pauseListener(@PathVariable String listenerName) {
        if (registry == null) {
            log.error("Registry is not available.");
            return;
        }
        try {
            MessageListenerContainer listenerContainer = registry.getListenerContainer(listenerName);
            if (listenerContainer == null) {
                log.warn("No listener container found with name: " + listenerName);
                return;
            }
            log.info("Attempting to pause listener: " + listenerName);
            listenerContainer.pause();
            log.info(listenerName + " pause is done.");
        } catch (Exception e) {
            log.error("Failed to pause listener " + listenerName + ": " + e.getMessage(), e);
        }
    }


    @Async
    protected void startListener(String listenerName) {
        try {
            Objects.requireNonNull(registry.getListenerContainer(listenerName)).start();
        } catch (Exception e) {
            log.error("Failed to start the eventListener", e);
        }
    }

    @Async
    protected void resumeListener(String listenerName) {
        try {
            Objects.requireNonNull(registry.getListenerContainer(listenerName)).resume();
        } catch (Exception e) {
            log.error("Failed to resume the eventListener", e);
        }
    }

    private boolean isListenerRunning(String listenerName) {
        try {
            return Objects.requireNonNull(registry.getListenerContainer(listenerName)).isRunning();
        } catch (Exception e) {
            log.error("Error checking if the eventListener is running", e);
            return false;
        }
    }
}
