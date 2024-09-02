package org.frank.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.response.ResultData;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class OrderController {

//    public static final String PAYMENT_SERVICE_URL = "http://localhost:8001";
    public static final String PAYMENT_SERVICE_URL = "http://cloud-payment-service";

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @GetMapping("/pay/add")
    public ResultData<?> addOrder(PayDTO payDTO) {
        String url = PAYMENT_SERVICE_URL + "/pay/add";
        return restTemplate.postForObject(url, payDTO, ResultData.class);
    }

    @GetMapping("/pay/{id}")
    public ResultData<?> getInfo(@PathVariable("id") Integer id) {
        String url = PAYMENT_SERVICE_URL + "/pay/" + id;
        return restTemplate.getForObject(url, ResultData.class);
    }

    @GetMapping("/pay/update")
    public ResultData<?> updateOrder(PayDTO payDTO) {
        String url = PAYMENT_SERVICE_URL + "/pay/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayDTO> request = new HttpEntity<>(payDTO, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, request, ResultData.class).getBody();
    }

    @GetMapping("/pay/{id}/delete")
    public ResultData<?> deleteOrder(@PathVariable("id") Integer id) {
        String url = PAYMENT_SERVICE_URL + "/pay/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, request, ResultData.class).getBody();
    }

    @GetMapping("/pay/config/info")
    public String getInfoOfConsul() {
        String url = PAYMENT_SERVICE_URL + "/pay/config/info";
        // 預設請求為輪詢
        return restTemplate.getForObject(url, String.class);
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
