package org.frank.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {

    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("id cannot be negative");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "Hello, circuit! inputId: " + id + " \t " + UUID.randomUUID().toString().replace("-", "");
    }

    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("bulkhead id cannot be negative");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "Hello, bulkhead! inputId: " + id + " \t " + UUID.randomUUID().toString().replace("-", "");

    }

    @GetMapping("/pay/threadPoolBulkhead/{id}")
    public String myThreadPoolBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("threadPool bulkhead id cannot be negative");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "Hello, thread pool bulkhead! inputId: " + id + " \t " + UUID.randomUUID().toString().replace("-", "");

    }

    @GetMapping("/pay/rateLimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "Hello, rateLimit! inputId: " + id + " \t " + UUID.randomUUID().toString().replace("-", "");
    }
}
