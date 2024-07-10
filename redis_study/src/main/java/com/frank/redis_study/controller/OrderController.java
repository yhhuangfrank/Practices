package com.frank.redis_study.controller;

import com.frank.redis_study.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "訂單 api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/add")
    @ApiOperation("新增訂單")
    public void addOrder() {
        orderService.addOrder();
    }

    @GetMapping("/orders/{keyId}")
    @ApiOperation("按照keyId查詢訂單")
    public String getOrder(@PathVariable("keyId") Integer keyId) {
        return orderService.getOrderKeyById(keyId);
    }

}
