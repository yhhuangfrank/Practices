package com.frank.redis_distributed_lock1.service;

import com.frank.redis_distributed_lock1.mylock.DistributedLockFactory;
import com.frank.redis_distributed_lock1.mylock.RedisDistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final StringRedisTemplate stringRedisTemplate;

    private final Lock lock = new ReentrantLock();

    private final DistributedLockFactory distributedLockFactory;

    private final RedissonClient redisson;

    @Value("${server.port}")
    private String port;

    /**
     * v8 使用官網推薦的Redisson(基於 Java 實現的 RedLock)
     */
    public String saleByRedisson() {
        String message = "";
        RLock redissonLock = redisson.getLock("frankRedisLock");
        redissonLock.lock();
        try {
            // 查詢庫存
            String key = "inventory001";
            String result = stringRedisTemplate.opsForValue().get(key);
            // 判斷庫存是否足夠
            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
            // 扣減庫存，每次減少一個
            if (inventoryNum > 0) {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
            } else {
                message = "商品賣完了...";
            }
        } finally {
            // 只能刪除自己 thread 的 key
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
        log.info("{} \t 服務 port: {}", message, port);
        return message + "\t" + "服務port:" + port;
    }

    /**
     * v7 加上自動增加到期時間的腳本，讓規定時間內沒有完成的業務能夠完成
     */
    public String sale() {
        String message = "";
        Lock redisLock = distributedLockFactory.getDistributedLock("REDIS");
        redisLock.lock();
        try {
            // 查詢庫存
            String key = "inventory001";
            String result = stringRedisTemplate.opsForValue().get(key);
            // 判斷庫存是否足夠
            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
            // 扣減庫存，每次減少一個
            if (inventoryNum > 0) {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
            } else {
                message = "商品賣完了...";
            }
            // 透過強制停止 60s 來測試自動加時
//            try {
//                TimeUnit.SECONDS.sleep(60L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        } finally {
            redisLock.unlock();
        }
        log.info("{} \t 服務 port: {}", message, port);
        return message + "\t" + "服務port:" + port;
    }

    /**
     * v6 透過 Lua script 進一步實踐可重入性, 與工廠模式獲得特定種類的 Lock
     * 缺點：若某個 thread 執行時間超過 ttl, 會導致 unlock 失敗
     */
//    public String sale() {
//        String message = "";
//        Lock redisLock = distributedLockFactory.getDistributedLock("REDIS");
//        redisLock.lock();
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//            } else {
//                message = "商品賣完了...";
//            }
//            testReEntry(); // 測試可重入性
//        } finally {
//            redisLock.unlock();
//        }
//        log.info("{} \t 服務 port: {}", message, port);
//        return message + "\t" + "服務port:" + port;
//    }

    private void testReEntry() {
        Lock redisLock = distributedLockFactory.getDistributedLock("REDIS");
        try {
            System.out.println("=================測試重新進入=================");
            redisLock.lock();
        } finally {
            redisLock.unlock();
        }
    }

    /**
     * v5 透過 Lua script 實踐判斷自己的鎖與釋放鎖
     * 缺點：
     * 對於鎖的可重入性並不支援
     */
//    public String sale() {
//        String message = "";
//        String lockKey = "frankLock";
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String uniqueId = uuid + ":" + Thread.currentThread().getId();
//        // 加上鎖與設置過期時間在同一命令下操作
//        while (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId, 30L, TimeUnit.SECONDS))) {
//            // 搶不到鎖的 thread 要暫停進行重試
//            try {
//                TimeUnit.MILLISECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                log.error("sale error !", e);
//            }
//        }
//        // 取得鎖，執行邏輯
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//            } else {
//                message = "商品賣完了...";
//            }
//        } finally {
//            // 只能刪掉自己的鎖與釋放鎖
//            String luaScript = """
//                        if redis.call('get',KEYS[1]) == ARGV[1] then
//                            return redis.call('del',KEYS[1])
//                        else
//                            return 0
//                        end
//                    """;
//            stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Boolean.class), List.of(lockKey), uniqueId);
//        }
//        log.info("{} \t 服務 port: {}", message, port);
//        return message + "\t" + "服務port:" + port;
//    }

    /**
     * v4 修改 釋放鎖時，確認是否是自己的
     * 隱患：
     * 最後判斷是否是自己的鎖與釋放鎖時，是兩步，不是原子性操作
     * 可透過
     * <a href="https://redis.io/docs/latest/develop/use/patterns/distributed-locks/">Lua script 編寫</a>
     * 來實現
     */
//    public String sale() {
//        String message = "";
//        String lockKey = "frankLock";
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String uniqueId = uuid + ":" + Thread.currentThread().getId();
//        // 加上鎖與設置過期時間在同一命令下操作
//        while (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId, 30L, TimeUnit.SECONDS))) {
//            // 搶不到鎖的 thread 要暫停進行重試
//            try {
//                TimeUnit.MILLISECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                log.error("sale error !", e);
//            }
//        }
//        // 取得鎖，執行邏輯
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//                log.info("{} \t 服務 port: {}", message, port);
//            } else {
//                message = "商品賣完了...";
//            }
//        } finally {
//            // 只能刪掉自己的鎖
//            if (uniqueId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
//                stringRedisTemplate.delete(lockKey); // 釋放鎖
//            }
//        }
//        return message + "\t" + "服務port:" + port;
//    }

    /**
     * v3 加上過期時間, 假設定為 30s
     * 隱患：
     * 當兩個有 thread A/B, A 若獲取鎖之後執行時間超過 30s, 那麼 B 會先獲取鎖,
     * 而 A 執行完就把鎖刪了, B 結束時就無法刪除鎖
     */
//    public String sale() {
//        String message = "";
//        String lockKey = "frankLock";
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String uniqueId = uuid + ":" + Thread.currentThread().getId();
//        // 加上鎖與設置過期時間在同一命令下操作
//        while (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId, 30L, TimeUnit.SECONDS))) {
//            // 搶不到鎖的 thread 要暫停進行重試
//            try {
//                TimeUnit.MILLISECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                log.error("sale error !", e);
//            }
//        }
//        // 取得鎖，執行邏輯
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//                log.info("{} \t 服務 port: {}", message, port);
//            } else {
//                message = "商品賣完了...";
//            }
//        } finally {
//            stringRedisTemplate.delete(lockKey); // 釋放鎖
//        }
//        return message + "\t" + "服務port:" + port;
//    }

    /**
     * v2.2 while 重試
     * 隱患：lockKey 沒有過期時間，若某個服務建立鎖後卻當機沒有走到 finally 區塊釋放鎖,
     * 其他服務就永遠拿不到鎖，產生死鎖
     */
//    public String sale() {
//        String message = "";
//        String lockKey = "frankLock";
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String uniqueId = uuid + ":" + Thread.currentThread().getId();
//
//        while (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId))) {
//            // 搶不到鎖的 thread 要暫停進行重試
//            try {
//                TimeUnit.MILLISECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                log.error("sale error !", e);
//            }
//        }
//        // 取得鎖，執行邏輯
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//                log.info("{} \t 服務 port: {}", message, port);
//            } else {
//                message = "商品賣完了...";
//            }
//        } finally {
//            stringRedisTemplate.delete(lockKey); // 釋放鎖
//        }
//        return message + "\t" + "服務port:" + port;
//    }

    /**
     * v2.1 遞迴重試，但可能有 stack overflow 風險
     */
//    public String sale() {
//        String message = "";
//        String lockKey = "frankLock";
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String uniqueId = uuid + ":" + Thread.currentThread().getId();
//
//        Boolean isSuccess = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId);
//
//        if (!Boolean.TRUE.equals(isSuccess)) {
//            // 搶不到鎖的 thread 要暫停進行重試
//            try {
//                TimeUnit.MILLISECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                log.error("sale error !", e);
//            }
//            sale();
//        } else {
//            // 取得鎖，執行邏輯
//            try {
//                // 查詢庫存
//                String key = "inventory001";
//                String result = stringRedisTemplate.opsForValue().get(key);
//                // 判斷庫存是否足夠
//                int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//                // 扣減庫存，每次減少一個
//                if (inventoryNum > 0) {
//                    stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                    message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//                    log.info("{} \t 服務 port: {}", message, port);
//                } else {
//                    message = "商品賣完了...";
//                }
//            } finally {
//                stringRedisTemplate.delete(lockKey); // 釋放鎖
//            }
//        }
//        return message + "\t" + "服務port:" + port;
//    }

    /**
     * v1 在多服務節點配合Nginx並發請求下會有超賣現象
     */
//    public String sale() {
//        String message;
//
//        lock.lock();
//        try {
//            // 查詢庫存
//            String key = "inventory001";
//            String result = stringRedisTemplate.opsForValue().get(key);
//            // 判斷庫存是否足夠
//            int inventoryNum = result == null ? 0 : Integer.parseInt(result);
//            // 扣減庫存，每次減少一個
//            if (inventoryNum > 0) {
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(--inventoryNum));
//                message = "成功賣出一個商品，庫存剩餘: " + inventoryNum;
//                log.info("{} \t 服務 port: {}", message, port);
//            } else {
//                message = "商品賣完了...";
//            }
//        } finally {
//            lock.unlock();
//        }
//
//        return message + "\t" + "服務port:" + port;
//    }
}
