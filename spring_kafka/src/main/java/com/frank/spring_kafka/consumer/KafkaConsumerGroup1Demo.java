package com.frank.spring_kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaConsumerGroup1Demo {

    public static void main(String[] args) {

        // 配置
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 配置消費的 offset 起點

        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerGroup1"); // 每個消費的 consumer group 會紀錄自己消費到哪個 offset

        // 自訂的消費分區策略
        configMap.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName()); // kafka 內建的輪詢策略
        // 自訂的 group 內的成員ID
        configMap.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "aaa");

        // 創建消費者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);

        // 訂閱主題
        consumer.subscribe(List.of("test1", "test2"));

        // 從 topic 中拉取數據
        while (true) {
            // 預設拉取的 offset 為 LEO (Log End Offset) = size + 1，即消費最新發送的數據
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.partition());
            }
        }

//        consumer.close();
    }
}
