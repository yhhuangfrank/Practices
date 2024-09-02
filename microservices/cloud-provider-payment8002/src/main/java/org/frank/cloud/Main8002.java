package org.frank.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient // for service discovery Consul
@RefreshScope // dynamic refreshing
public class Main8002 {

    public static void main(String[] args) {
        SpringApplication.run(Main8002.class, args);
    }
}
