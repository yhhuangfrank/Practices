package com.frank.spring_kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerInterceptorTest {

    public static void main(String[] args) {

        // 配置
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 可在送出數據時，做攔截
        // configMap.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ValueInterCeptor.class);

        // 冪等性
//        configMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
//        configMap.put(ProducerConfig.RETRIES_CONFIG, 5);
//        configMap.put(ProducerConfig.BATCH_SIZE_CONFIG, 5);
//        configMap.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);

        // 創建生產者
        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);

        // 創建數據
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", "key" + i, "value" + i);
            // producer.send(producerRecord);  // 發送數據
            // 異步發送
             producer.send(producerRecord, (recordMetadata, e) -> {
                 if (e != null) {
                     System.out.println("數據發送失敗");
                     e.printStackTrace();
                 } else {
                     System.out.printf("Kafka Sender Thread 數據發送成功！！，數據: %s%n", recordMetadata);
                 }
             }); // 發送數據，可傳入一個 callback 用來確認是否發送成功
            System.out.println("主程序發送給 Kafka RecordAccumulator 完成!");
        }

        producer.close();
    }
}
