package com.frank.redis_study.controller;

import com.frank.redis_study.service.HyperLogLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "大數據的統計")
public class HyperLogLogController {

    private final HyperLogLogService hyperLogLogService;

    @ApiOperation("獲取去除重複後的 uv 訪問量")
    @GetMapping("/uv")
    public long uv() {
        return hyperLogLogService.uv();
    }
}
