package com.frank.redis_distributed_lock1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final StringRedisTemplate stringRedisTemplate;

    private final Lock lock = new ReentrantLock();

    @Value("${server.port}")
    private String port;

    /**
     * v2.2 while 重試
     * 隱患：lockKey 沒有過期時間，若某個服務建立鎖後卻當機沒有走到 finally 區塊釋放鎖,
     * 其他服務就永遠拿不到鎖
     */
    public String sale() {
        String message = "";
        String lockKey = "frankLock";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String uniqueId = uuid + ":" + Thread.currentThread().getId();

        while (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uniqueId))) {
            // 搶不到鎖的 thread 要暫停進行重試
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                log.error("sale error !", e);
            }
        }
        // 取得鎖，執行邏輯
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
                log.info("{} \t 服務 port: {}", message, port);
            } else {
                message = "商品賣完了...";
            }
        } finally {
            stringRedisTemplate.delete(lockKey); // 釋放鎖
        }
        return message + "\t" + "服務port:" + port;
    }

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
