package org.frank.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // register to Consul
@EnableFeignClients // activate feign client
public class MainOpenFeign80 {

    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign80.class);
    }
}
