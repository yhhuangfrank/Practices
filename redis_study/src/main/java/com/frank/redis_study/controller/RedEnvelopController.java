package com.frank.redis_study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redEnvelop")
@RequiredArgsConstructor
public class RedEnvelopController {

    public static final String RED_ENVELOPE_KEY = "redEnvelope:";

    public static final String RED_ENVELOPE_CONSUMER_KEY = "redEnvelope:consumer:";

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/send")
    public String sendRedEnvelope(Integer total, Integer numOfEnvelopes) {
        // 將總金額 total 拆分為 numOfEnvelopes 個子紅包
        Integer[] split = splitRedEnvelopes(total, numOfEnvelopes);
        String key = RED_ENVELOPE_KEY + UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForList().leftPushAll(key, split);
        redisTemplate.expire(key, 1L, TimeUnit.DAYS);
        return "success set redEnvelope, key: %s split RedEnvelopes: %s".formatted(key, Arrays.toString(split));
    }

    @GetMapping("/get")
    public String getRedEnvelopeMoney(String userId, String redEnvelopeKey) {
        // 是否搶過紅包
        String key = RED_ENVELOPE_CONSUMER_KEY + redEnvelopeKey;
        Object result = redisTemplate.opsForHash().get(key, userId);
        if (result != null) return "already get before ...";
        Object money = redisTemplate.opsForList().leftPop(RED_ENVELOPE_KEY + redEnvelopeKey);
        if (money == null) return "current RedEnvelope is empty!";
        // 紀錄
        log.info("user: {} get {} money", userId, money);
        redisTemplate.opsForHash().put(key, userId, money);
        return "get %s money".formatted(money);
    }

    private Integer[] splitRedEnvelopes(int total, int numOfEnvelopes) {
        Integer[] res = new Integer[numOfEnvelopes];
        int current = total;
        int remained = numOfEnvelopes;
        for (int i = 0; i < numOfEnvelopes; i++) {
            if (i == numOfEnvelopes - 1) {
                res[i] = current; // 最後一個紅包不需要隨機數
                break;
            }
            int max = (current / remained) * 2; // 計算獲取上限
            int num = ThreadLocalRandom.current().nextInt(1, max + 1);
            res[i] = num;
            current -= num;
            remained--;
        }
        return res;
    }
}
