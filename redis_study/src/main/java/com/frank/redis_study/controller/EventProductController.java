package com.frank.redis_study.controller;

import com.frank.redis_study.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Api(tags = "活動商品 API")
@Slf4j
public class EventProductController {

    public static final String EVENT_KEY = "event:";
    public static final String EVENT_KEY_A = "event:a";
    public static final String EVENT_KEY_B = "event:b";

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 分頁查詢
     *
     * @param page 第幾頁
     * @param size 每頁幾個
     * @return 商品列表
     */
    @ApiOperation("每次一頁，每頁面五條紀錄")
    @GetMapping("/find")
    public List<Product> find(int page, int size) {
        List<Product> products = null;
        long start = (page - 1) * size;
        long end = start + size - 1;

        try {
            // 使用 redis 中 lrange 實現查詢與分頁
            products = redisTemplate.opsForList().range(EVENT_KEY, start, end).stream().map(Product.class::cast).toList();
            if (CollectionUtils.isEmpty(products)) {
                // TODO 到 DB 查詢
            }
            log.info("活動商品", products);
        } catch (Exception e) {
            log.error("event exception ", e);
            e.printStackTrace();
        }

        return products;
    }

    @ApiOperation("每次一頁，每頁面五條紀錄")
    @GetMapping("/findAB")
    public List<Product> findAB(int page, int size) {
        List<Product> products = null;
        long start = (page - 1) * size;
        long end = start + size - 1;

        try {
            // 使用 redis 中 lrange 實現查詢與分頁
            List<Object> range = redisTemplate.opsForList().range(EVENT_KEY_A, start, end);
            if (CollectionUtils.isEmpty(range)) {
                log.info("cacheA 已經過期或失效");
                range = redisTemplate.opsForList().range(EVENT_KEY_B, start, end);
                if (CollectionUtils.isEmpty(range)) {
                    log.info("cacheB 已經過期或失效");
                    log.warn("Both of cacheA and cacheB are all empty! need search for MySQL");
                    // TODO 到 DB 查詢
                }
            } else {
                products = range.stream().map(Product.class::cast).toList();
            }
            log.info("活動商品", products);
        } catch (Exception e) {
            log.error("event exception ", e);
            e.printStackTrace();
        }

        return products;
    }
}
