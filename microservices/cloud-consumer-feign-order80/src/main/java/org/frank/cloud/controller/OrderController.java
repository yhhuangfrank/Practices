package org.frank.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.frank.cloud.apis.PayFeignApi;
import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.response.ResultData;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/feign")
@RequiredArgsConstructor
public class OrderController {

    private final DiscoveryClient discoveryClient;

    private final PayFeignApi payFeignApi;

    @PostMapping("/pay/add")
    public ResultData<Pay> addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/pay/{id}")
    public ResultData<Pay> getInfo(@PathVariable("id") Integer id) {
        return payFeignApi.findById(id);
    }

    @GetMapping("/pay")
    public ResultData<List<Pay>> getAll() {
        return payFeignApi.findAll();
    }

    @PutMapping("/pay/update")
    public ResultData<Pay> updateOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }

    @DeleteMapping("/pay/{id}")
    public ResultData<String> deleteOrder(@PathVariable("id") Integer id) {
        return payFeignApi.delPay(id);
    }

    @GetMapping("/pay/config/info")
    public String getInfoOfConsul() {
        return payFeignApi.loadBalancer();
    }

    // get current services on Consul
    @GetMapping("/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }

        System.out.println("============");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }
}
