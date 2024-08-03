package com.frank.redis_study.controller;

import com.frank.redis_study.entity.Customer;
import com.frank.redis_study.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Api(tags = "使用 BloomFilter 過濾測試")
@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ApiOperation("加入 customer")
    @PostMapping("/add")
    public void addCustomer() {
        for (int i = 0; i < 2; i++) {
            Customer customer = Customer.builder()
                    .name(String.valueOf(i))
                    .age(ThreadLocalRandom.current().nextInt(31))
                    .createTime(LocalDateTime.now())
                    .phone("xxxx-xxx-xxx")
                    .build();
            customerService.addCustomer(customer);
        }
    }

    @ApiOperation("查詢 customer")
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("customerId") Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
}
