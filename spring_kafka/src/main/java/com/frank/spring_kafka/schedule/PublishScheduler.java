package com.frank.spring_kafka.schedule;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishScheduler {

    @Value("${kafka.pub.deviceId}")
    private String DEVICE_ID = "100081";

    @Value("${kafka.pub.deviceName}")
    private String DEVICE_NAME = "k_o_p-o-p";

    @Value("${kafka.pub.measurePoint}")
    private String MEASURE_POINT = "ONOFF";

    @Value("${kafka.pub.siteId}")
    private String SITE_ID = "shawn";

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * message template
     *         {
     *             "agentId": "48b02ba6-5409-41a1-a724-7f4656f5b976",
     *             "measurePoint": "HMVL3-17",
     *             "messageId": "989c6ac0-c093-40e4-b114-9eca902a20b2",
     *             "siteId": "HK05",
     *             "deviceName": "DCT2_1F_PDU-POC-13A1-A09_PM",
     *             "deviceId": "5301",
     *             "value": 1.0413866,
     *             "ts": 1723617046026
     *         }
     */
//    @Scheduled(cron = "*/2 * * * * *")
    public void publishMessage() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("agentId", "kafka_scheduler");
            jsonObject.addProperty("measurePoint", MEASURE_POINT);
            jsonObject.addProperty("messageId", "telemetry_testing");
            jsonObject.addProperty("siteId", SITE_ID);
            jsonObject.addProperty("deviceName", DEVICE_NAME);
            jsonObject.addProperty("deviceId", DEVICE_ID);
            jsonObject.addProperty("value", ThreadLocalRandom.current().nextInt(1001));
            jsonObject.addProperty("ts", Instant.now().toEpochMilli());
            String key = DEVICE_ID + "-" + MEASURE_POINT;
            kafkaTemplate.send("telemetry", jsonObject.toString());
            log.info("success to send to telemetry key: {}, message : {}", key, jsonObject);
        } catch (Exception e) {
            log.error("error in PublishScheduler.publishMessage !", e);
        }
    }
}
