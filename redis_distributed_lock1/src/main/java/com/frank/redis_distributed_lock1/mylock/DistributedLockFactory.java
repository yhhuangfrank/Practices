package com.frank.redis_distributed_lock1.mylock;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.locks.Lock;

@Component
@RequiredArgsConstructor
public class DistributedLockFactory {

    private final StringRedisTemplate stringRedisTemplate;
    private String lockName;

    private final String uuid = UUID.randomUUID().toString().replace("-", "");

    public Lock getDistributedLock(String lockType) {
        if (lockType == null) return null;
        if (lockType.equals("REDIS")) {
            this.lockName = "frankRedisLock";
            return new RedisDistributedLock(stringRedisTemplate, lockName, uuid);
        } else if (lockType.equals("MYSQL")) {
            this.lockName = "frankMySQLLock";
            return null;
        }
        return null;
    }
}
