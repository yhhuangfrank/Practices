package org.frank.cloud.apis;

import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-payment-service")
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
}
