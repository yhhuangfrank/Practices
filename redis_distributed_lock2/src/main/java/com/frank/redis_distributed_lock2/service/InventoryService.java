package com.frank.redis_distributed_lock2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

    public String sale() {
        String message;

        lock.lock();
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
                System.out.println(message + "\t" + "服務port:" + port);
            } else {
                message = "商品賣完了...";
            }
        } finally {
            lock.unlock();
        }

        return message + "\t" + "服務port:" + port;
    }
}
