package com.frank.redis_study.service;

import com.frank.redis_study.adaptor.LocalDateTimeAdaptor;
import com.frank.redis_study.entity.Customer;
import com.frank.redis_study.repository.CustomerRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    public static final String CACHE_CUSTOMER_KEY = "customer:";

    private final CustomerRepository customerRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    public void addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        // 儲存完後寫入 redis
        String key = CACHE_CUSTOMER_KEY + savedCustomer.getId();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdaptor()).create();
        redisTemplate.opsForValue().set(key, gson.toJson(savedCustomer));
    }

    public Customer findCustomerById(Integer customerId) {
        Customer customer;
        // 先到 redis 查詢
        String key = CACHE_CUSTOMER_KEY + customerId;
        customer = (Customer) redisTemplate.opsForValue().get(key);
        if (customer != null) {
            return customer;
        }
        // redis 沒有
        customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdaptor()).create();
            redisTemplate.opsForValue().set(key, gson.toJson(customer)); // 回寫到 redis
        }
        // mysql 也沒有
        log.info("redis & mysql not found");
        System.out.println(customer);
        return customer;
    }
}
