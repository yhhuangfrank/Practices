package com.frank.redis_study.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GuavaBloomFilterService {

    public static final int TEN_THOUSAND = 10000;

    public static final int SIZE = 100 * TEN_THOUSAND;

    // 誤判率。False Positive Probability
    // 數字越小代表誤判個數越少，但同時要求bits, hash function 數量就越多，執行時間可能就越慢
    public static final double FPP = 0.03; // 同時也是 guava 預設 fpp 值

    public static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE, FPP);

    public void guavaFilter() {
        // 添加 100 萬個白名單
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(i);
        }
        // 故意查找 10 萬個不存在的數據
        List<Integer> list = new ArrayList<>();
        for (int i = SIZE; i < (SIZE + 10 * TEN_THOUSAND); i++) {
            if (bloomFilter.mightContain(i)) {
                log.info("誤判了 i = {}", i);
                list.add(i);
            }
        }
        log.info("誤判總數量:{}", list.size());
    }
}
