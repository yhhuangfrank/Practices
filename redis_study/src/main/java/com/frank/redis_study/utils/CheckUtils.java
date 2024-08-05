package com.frank.redis_study.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean checkWithBloomFilter(String checkItem, String key) {
        int hashValue = key.hashCode();
        long index = Math.abs((long) (hashValue % Math.pow(2, 32)));
        boolean isExist = Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(checkItem, index));
        log.info("key: {}, 對應 index: {} 是否存在 {}", key, index, isExist);
        return isExist;
    }
}
