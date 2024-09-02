package org.frank.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // need config LoadBalanced in RestTemplate for service in Consul
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
