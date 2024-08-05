package com.frank.redis_study.service;

import com.frank.redis_study.adaptor.LocalDateTimeAdaptor;
import com.frank.redis_study.entity.Customer;
import com.frank.redis_study.repository.CustomerRepository;
import com.frank.redis_study.utils.CheckUtils;
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

    private final CheckUtils checkUtils;

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
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdaptor()).create();
        customer = gson.fromJson((String) redisTemplate.opsForValue().get(key), Customer.class);
        if (customer != null) {
            return customer;
        }
        // redis 沒有, 則查找 MySQL
        customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            redisTemplate.opsForValue().set(key, gson.toJson(customer)); // 回寫到 redis
        } else {
            // mysql 也沒有
            log.info("redis & mysql not found");
        }
        log.info("customer is {}", customer);
        return customer;
    }

    /**
     * 使用 BloomFilter 作為白名單過濾, BloomFilter -> redis -> mysql
     * 白名單redis key : whitelistCustomer
     * @param customerId
     * @return
     */
    public Customer findCustomerByIdWithBloomFilter(Integer customerId) {
        String key = CACHE_CUSTOMER_KEY + customerId;
        // BloomFilter 先確認，若沒有就返回，有則繼續往後做
        String whiteListKey = "whitelistCustomer";
        if (!checkUtils.checkWithBloomFilter(whiteListKey, key)) {
            log.info("customerId:{} didn't in whitelist", customerId);
            return null;
        }
        return findCustomerById(customerId);
    }
}
