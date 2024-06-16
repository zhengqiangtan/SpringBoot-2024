package com.tan.kafka.config;


import com.tan.kafka.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    /**
     * 创建一个Kafka消费者工厂，用于生产特定配置的Kafka消费者。
     * @param consumerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory(ConsumerFactory<String, Event> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        log.info("KafkaListenerContainerFactory created.");
        return factory;
    }


    /**
     * 创建一个Kafka消费者工厂，用于生产特定配置的Kafka消费者。
     * 这个方法的主要作用是配置消费者的各项参数，包括连接服务器、消费者组ID、序列化器等，
     * 以便消费者能够正确地从Kafka主题中消费消息。
     *
     * @return ConsumerFactory<String, Event> 返回一个配置好的消费者工厂，用于创建字符串键和Event值的消费者实例。
     */
    @Bean
    public ConsumerFactory<String, Event> consumerFactory() {
        // 初始化配置属性映射，用于设置消费者的配置参数。
        Map<String, Object> configProps = new HashMap<>();
        // 配置Kafka服务器的连接地址和端口。
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置消费者的组ID，用于标识消费者属于哪个消费组。
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "events-group");

        // 配置键和值的反序列化器，这里使用StringDeserializer和JsonDeserializer。
        // StringDeserializer用于反序列化键，JsonDeserializer用于反序列化值，并且信任所有包，设置默认值类型为Event类。
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Event.class);

        // 使用配置属性映射创建并返回一个默认的Kafka消费者工厂。
        return new DefaultKafkaConsumerFactory<>(configProps);
    }
}
