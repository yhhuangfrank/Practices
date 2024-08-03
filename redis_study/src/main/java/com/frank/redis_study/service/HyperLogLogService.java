package com.frank.redis_study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class HyperLogLogService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 模擬後台有用戶(IP)點擊網頁, 共 200 個
     */
    @PostConstruct
    public void init() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    String s = String.valueOf(ThreadLocalRandom.current().nextInt(256));
                    list.add(s);
                }
                String ip = String.join(".", list);
                Long count = redisTemplate.opsForHyperLogLog().add("ipHLL", ip);
                log.info("ip 地址為： {}, 訪問次數： {}", ip, count);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    log.error("error!", e);
                }
            }
        }, "IP_thread");
//        thread.start();
    }

    public long uv() {
        // PFCOUNT
        return redisTemplate.opsForHyperLogLog().size("ipHLL");
    }
}
