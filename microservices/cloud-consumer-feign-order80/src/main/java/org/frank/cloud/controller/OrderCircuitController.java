package org.frank.cloud.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.frank.cloud.apis.PayFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class OrderCircuitController {

    private final PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    // final method when fail
    public String myCircuitFallback(Integer id, Throwable t) {
        return "myCircuitFallback called, system is busy... param: %s, error message: %s".formatted(id, t.getMessage());
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkHeadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkHead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    // final method when fail
    public String myBulkHeadFallback(Integer id, Throwable t) {
        return "myBulkHeadFallback called because current bulkhead is saturated";
    }


    @GetMapping("/feign/pay/threadPoolBulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myThreadPoolBulkHeadFallback", type = Bulkhead.Type.SEMAPHORE)
    public CompletableFuture<String> myThreadPoolBulkHead(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t -- begin");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t -- end");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myThreadPoolBulkhead(id) + " by thread pool");
    }

    // final method when fail
    public CompletableFuture<String> myThreadPoolBulkHeadFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "myThreadPoolBulkHeadFallback called because current bulkhead is saturated");
    }

    @GetMapping("/feign/pay/rateLimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimitFallback")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return  payFeignApi.myRateLimit(id);
    }

    // final method when fail
    public String myRateLimitFallback(Integer id, Throwable t) {
        return "myRateLimitFallback called because current high throughput";
    }
}
