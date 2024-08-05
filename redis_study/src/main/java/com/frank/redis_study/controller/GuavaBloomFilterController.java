package com.frank.redis_study.controller;

import com.frank.redis_study.service.GuavaBloomFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "google guava bloomFilter")
@RequiredArgsConstructor
public class GuavaBloomFilterController {

    private final GuavaBloomFilterService guavaBloomFilterService;

    @ApiOperation("測試guava bloom filter 測試 100萬條數據中，10萬個不存在的數據")
    @GetMapping("/guavafilter")
    public void guavaFilter() {
        guavaBloomFilterService.guavaFilter();
    }
}
