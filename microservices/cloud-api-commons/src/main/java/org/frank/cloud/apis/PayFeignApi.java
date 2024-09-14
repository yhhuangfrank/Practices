package org.frank.cloud.apis;

import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway") // make feign request go through gateway
public interface PayFeignApi {

    @PostMapping("/pay/add")
    ResultData<Pay> addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/{id}")
    ResultData<String> delPay(@PathVariable("id") Integer id);

    @PutMapping("/pay/update")
    ResultData<Pay> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/{id}")
    ResultData<Pay> findById(@PathVariable("id") Integer id);

    @GetMapping("/pay")
    ResultData<List<Pay>> findAll();

    @GetMapping("/pay/config/info")
    String loadBalancer();

    /**
     * test circuit breaker
     */
    @GetMapping("/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * test bulkhead
     */
    @GetMapping("/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * test threadPool bulkhead
     */
    @GetMapping("/pay/threadPoolBulkhead/{id}")
    String myThreadPoolBulkhead(@PathVariable("id") Integer id);

    /**
     * test rateLimit
     */
    @GetMapping("/pay/rateLimit/{id}")
    String myRateLimit(@PathVariable("id") Integer id);

    /**
     * test Micrometer
     */
    @GetMapping("/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/get/{id}")
    ResultData<Pay> getById(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
