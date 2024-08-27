package com.frank.spring_kafka.consumer;

import com.frank.spring_kafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SpringKafkaConsumer {

    @KafkaListener(topics = KafkaConfig.TOPIC_TEST, groupId = KafkaConfig.GROUP_ID)
    public void topicTest(List<String> messages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        for (String message : messages) {
            log.info("{} 消費了 topic: {}, 數據： {}", KafkaConfig.GROUP_ID, topic, message);
        }
    }
}
