package com.frank.redis_study;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化白名單, redis key = whitelistCustomer
 * BloomFilter有，redis才可能有資料
 * BloomFilter沒有，redis就沒有資料，也不用去MySQL查詢
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BloomFilterInit {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        String key = "customer:10"; // 設定此 key 在白名單中
        int hashValue = Math.abs(key.hashCode());
        // 透過取餘數來獲取 bitmap (長度為 2^32 bit) 中對應 index
        long idx = (long) (hashValue % Math.pow(2, 32));
        log.info("{} 對應的 index: {}", key, idx);
        // 設置對應bitmap index 的該 bit 值為 1, 代表白名單
        redisTemplate.opsForValue().setBit("whitelistCustomer", idx, true);
    }
}
