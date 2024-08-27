package com.frank.spring_kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerTransactionTest {

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
        // 在冪等性之上，加入 Transaction 配置
        configMap.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-tx-id");

        // 創建生產者
        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
        try {
            // 開啟交易
            producer.beginTransaction();

            // 創建數據
            for (int i = 0; i < 10; i++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", "key" + i, "value" + i);
                producer.send(producerRecord);  // 發送數據
            }

            producer.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            producer.abortTransaction(); // 終止交易
        }
    }
}
