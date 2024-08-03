package com.frank.redis_study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class OrderService {

    public static final String ORDER_KEY = "ord:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    public void addOrder() {
        int keyId = ThreadLocalRandom.current().nextInt(1000);
        String serialNo = UUID.randomUUID().toString();

//        stringRedisTemplate.opsForValue().set(ORDER_KEY + keyId, "序列號:" + serialNo);
        redisTemplate.opsForValue().set(ORDER_KEY + keyId, "序列號:" + serialNo);
        log.info(ORDER_KEY + keyId);
        log.info(serialNo);
    }

    public String getOrderKeyById(Integer keyId) {
//        return stringRedisTemplate.opsForValue().get(ORDER_KEY + keyId);
        return (String) redisTemplate.opsForValue().get(ORDER_KEY + keyId);
    }
}
