package com.frank.redis_redlock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RedLockController {

    public static final String CACHE_RED_LOCK = "frankRedLock";

    private final RedissonClient redissonClient1;

    private final RedissonClient redissonClient2;

    private final RedissonClient redissonClient3;

    @GetMapping("/multiLock")
    public String multiLock() {
        String currentThreadId = String.valueOf(Thread.currentThread().getId());

        RLock lock1 = redissonClient1.getLock(CACHE_RED_LOCK);
        RLock lock2 = redissonClient2.getLock(CACHE_RED_LOCK);
        RLock lock3 = redissonClient3.getLock(CACHE_RED_LOCK);

        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(lock1, lock2, lock3);

        redissonMultiLock.lock();
        try {
            log.info("in multiLock, current thread {}", currentThreadId);
            try {
                TimeUnit.SECONDS.sleep(30L);
            } catch (InterruptedException e) {
                log.error("error!", e);
            }
            log.info("task is over, current thread {}", currentThreadId);
        } catch (Exception e) {
            log.error("出現錯誤", e);
        } finally {
            redissonMultiLock.unlock();
            log.info("釋放分布式鎖成功, key: {}", CACHE_RED_LOCK);
        }

        return "multiLock task finish..." + currentThreadId;
    }
}
