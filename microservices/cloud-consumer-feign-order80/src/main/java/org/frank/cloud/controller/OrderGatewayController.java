package org.frank.cloud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.frank.cloud.apis.PayFeignApi;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.response.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/feign/pay/gateway")
@RestController
@RequiredArgsConstructor
public class OrderGatewayController {

    private final PayFeignApi payFeignApi;

    @GetMapping("/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    public String myGatewayFallback(Integer id, Throwable t) {
        log.error("input id: {}, myGatewayFallback called! ", id, t);
        return t.getMessage();
    }

    @GetMapping("/info")
    public ResultData<String> getGatewayInfo() {
        return payFeignApi.getGatewayInfo();
    }
}
