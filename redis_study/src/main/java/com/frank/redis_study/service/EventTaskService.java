package com.frank.redis_study.service;

import com.frank.redis_study.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventTaskService {

    public static final String EVENT_KEY = "event:";
    public static final String EVENT_KEY_A = "event:a";
    public static final String EVENT_KEY_B = "event:b";

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 模擬從 DB 中取數據
     *
     * @return
     */
    private List<Product> getProductsFromMySQL() {
        List<Product> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            int n = ThreadLocalRandom.current().nextInt(1000);
            Product product = Product.builder()
                    .productId(i)
                    .name("product" + i)
                    .price(n)
                    .description("detail" + i)
                    .build();
            list.add(product);
        }
        return list;
    }

    //@PostConstruct
    private void init() {
        log.info("啟動後台定時任務開始.........");
        // 用 thread 模擬定時任務，定期撈取參加活動的商品刷新到 Redis 中
        new Thread(() -> {
            while(true) {
                List<Product> products = getProductsFromMySQL();
                // 刪除舊的商品，加入新的商品
                redisTemplate.delete(EVENT_KEY);
                redisTemplate.opsForList().leftPushAll(EVENT_KEY, products.toArray());
                // 暫停一分鐘，相當於一分鐘刷新一次活動商品列表
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "event_thread").start();
    }

    @PostConstruct
    private void initAB() {
        log.info("啟動後台定時任務開始建構雙快取A,B.........");
        // 用 thread 模擬定時任務，定期撈取參加活動的商品刷新到 Redis 中
        new Thread(() -> {
            while (true) {
                log.info("update cacheA and cacheB");
                List<Product> products = getProductsFromMySQL();
                // 先更新快取B, 並且讓過期時間大過快取A, 防止 redis key 失效
                redisTemplate.delete(EVENT_KEY_B);
                redisTemplate.opsForList().leftPushAll(EVENT_KEY_B, products.toArray());
                redisTemplate.expire(EVENT_KEY_B, 86410L, TimeUnit.SECONDS); // 過期時間大於 A
                // 再更新快取A
                redisTemplate.delete(EVENT_KEY_A);
                redisTemplate.opsForList().leftPushAll(EVENT_KEY_A, products.toArray());
                redisTemplate.expire(EVENT_KEY_A, 86400L, TimeUnit.SECONDS);
                // 暫停一分鐘，相當於一分鐘刷新一次活動商品列表
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "event_thread").start();
    }
}
