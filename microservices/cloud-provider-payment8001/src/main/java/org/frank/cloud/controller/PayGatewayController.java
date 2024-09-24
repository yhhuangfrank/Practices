package org.frank.cloud.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.response.ResultData;
import org.frank.cloud.service.PayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/pay/gateway")
@RequiredArgsConstructor
public class PayGatewayController {

    private final PayService payService;

    @GetMapping("/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id cannot be negative");
        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/info")
    public ResultData<String> getGatewayInfo() {
        return ResultData.success("gateway info test: " + UUID.randomUUID().toString().replace("-", ""));
    }

    @GetMapping("/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.printf("request headerName %s head value: %s %n", headName, headValue);

            if (headName.equalsIgnoreCase("X-Request-frank1") ||
            headName.equalsIgnoreCase("X-Request-frank2")) {
                result = result + headName + "\t" + headValue + " ";
            }
        }

        return ResultData.success("gateway filter test: " + result + "\t" + System.currentTimeMillis());
    }
}
