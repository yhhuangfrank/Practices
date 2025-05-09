package com.frank.spring_kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KafkaConsumerDemo {

    public static void main(String[] args) {

        // 配置
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 配置消費的 offset 起點

        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerGroup1"); // 每個消費的 consumer group 會紀錄自己消費到哪個 offset

        // 設定交易的隔離級別
        configMap.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed"); // 正確提交的數據才能消費

        // 創建消費者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);

        // 訂閱主題
        consumer.subscribe(List.of("test"));

        // 指定 offset 方式
        boolean flag = true;
        while (flag) {
            consumer.poll(Duration.ofMillis(100));
            Set<TopicPartition> assignment = consumer.assignment();
            if (assignment != null) {
                for (TopicPartition topicPartition : assignment) {
                    if ("test".equals(topicPartition.topic())) {
                        consumer.seek(topicPartition, 2); // 指定從 offset 2 開始消費
                        flag = false;
                    }
                }
            }
        }

        // 從 topic 中拉取數據
        while (true) {
            // 預設拉取的 offset 為 LEO (Log End Offset) = size + 1，即消費最新發送的數據
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
        }

//        consumer.close();
    }
}
