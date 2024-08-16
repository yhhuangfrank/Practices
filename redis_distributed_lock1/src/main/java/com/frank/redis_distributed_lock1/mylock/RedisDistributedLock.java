package com.frank.redis_distributed_lock1.mylock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 從 DistributedLockFactory 工廠獲得
 */
@Slf4j
public class RedisDistributedLock implements Lock {

    private StringRedisTemplate stringRedisTemplate;

    private String lockName; // KEYS[1]
    private String uuidValue; // ARGV[1]
    private long expireTime; // ARGV[2]

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, String lockName, String uuid) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = uuid + ":" + Thread.currentThread().getId();
        this.expireTime = 30L;
    }

    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time == -1L) {
            System.out.println("lock(): lockName: " + lockName + "\t uuidValue: " + uuidValue);
            String script = """
                    if redis.call('EXISTS', KEYS[1]) == 0 or redis.call('HEXISTS', KEYS[1], ARGV[1]) == 1 then
                        redis.call('HINCRBY', KEYS[1], ARGV[1], 1)
                        redis.call('EXPIRE', KEYS[1], ARGV[2])
                        return 1
                    else
                        return 0
                    end
                    """;
            log.info("lockName: {}, uuidValue: {}", lockName, uuidValue);

            while (!Boolean.TRUE.equals(stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), List.of(lockName), uuidValue, String.valueOf(expireTime)))) {
                // 加鎖失敗，等待重試
                try {
                    TimeUnit.MILLISECONDS.sleep(60L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 加鎖成功，新建一個程序來掃描key的 ttl, 不足則加時間
            renewExpire();
            return true;
        }
        return false;
    }

    @Override
    public void unlock() {
        System.out.println("unlock(): lockName: " + lockName + "\t uuidValue: " + uuidValue);
        String script = """
                    if redis.call('HEXISTS', KEYS[1], ARGV[1]) == 0 then
                        return nil
                    elseif redis.call('HINCRBY', KEYS[1], ARGV[1], -1) == 0 then
                        return redis.call('DEL', KEYS[1])
                    else
                        return 0
                    end
                """;
        // nil = false, 0 = false, 1 = true
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), List.of(lockName), uuidValue);
        if (flag == null) {
            throw new RuntimeException("this lock " + uuidValue + " didn't exist");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private void renewExpire() {
        String script = """
                    if redis.call('HEXISTS', KEYS[1], ARGV[1]) == 1 then
                        return redis.call('EXPIRE', KEYS[1], ARGV[2])
                    else
                        return 0
                    end
                """;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (Boolean.TRUE.equals(stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), List.of(lockName), uuidValue, String.valueOf(expireTime)))) {
                    renewExpire();
                }
            }
        }, (this.expireTime * 1000) / 3);
    }
}
