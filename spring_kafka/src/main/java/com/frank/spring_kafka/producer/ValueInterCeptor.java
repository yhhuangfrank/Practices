package com.frank.spring_kafka.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ValueInterCeptor  implements ProducerInterceptor<String, String> {

    /**
     * 發送數據的時候調用此方法
     * @param producerRecord
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return null;
    }

    /**
     * 發送數據完畢，服務器給予 response 時調用此方法
     * @param recordMetadata
     * @param e
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    /**
     * Producer 關閉時調用
     */
    @Override
    public void close() {

    }

    /**
     * 創建生產者物件時調用
     * @param map
     */
    @Override
    public void configure(Map<String, ?> map) {

    }
}
