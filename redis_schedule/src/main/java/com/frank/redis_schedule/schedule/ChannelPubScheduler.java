package com.frank.redis_schedule.schedule;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelPubScheduler {

    private final StringRedisTemplate stringRedisTemplate;

    @Value("${redis.pub.channel.prefix}")
    private String CHANNEL_PREFIX = "telemetry_sub_";

    @Value("${redis.pub.deviceId}")
    private String DEVICE_ID = "1047";

    @Value("${redis.pub.deviceName}")
    private String DEVICE_NAME = "1_2_test-3-01";

    @Value("${redis.pub.measurePoint}")
    private String MEASURE_POINT;

    @Value("${redis.pub.siteId}")
    private String SITE_ID = "HK05";

    private static final List<Integer> numList = new ArrayList<>();

    private final AtomicInteger idx = new AtomicInteger(0);

    @PostConstruct
    private void init() {
        for (int i = 0; i < 101; i++) {
            numList.add(i);
        }
    }

    @Scheduled(cron = "*/2 * * * * *")
    public void publish() {
        int i;
        if (idx.get() == 100) {
            i = idx.getAndSet(0);
        } else {
            i = idx.getAndIncrement();
        }
        JsonObject json = new JsonObject();
        json.addProperty("agentId", "schedule_agent");
        json.addProperty("measurePoint", MEASURE_POINT);
        json.addProperty("messageId", "telemetry_testing");
        json.addProperty("siteId", SITE_ID);
        json.addProperty("deviceName", DEVICE_NAME);
        json.addProperty("deviceId", DEVICE_ID);
        json.addProperty("value", numList.get(i));
//        json.addProperty("value", 100);
        json.addProperty("ts", Instant.now().toEpochMilli());
        String channel = CHANNEL_PREFIX + DEVICE_ID;
        stringRedisTemplate.convertAndSend(channel, json.toString());

        log.info("success to publish to channel:{}, message is {}", channel, json);
    }
}
