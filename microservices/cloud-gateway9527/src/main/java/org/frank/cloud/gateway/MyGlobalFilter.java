package org.frank.cloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_TIME = "begin_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginTime = exchange.getAttribute(BEGIN_TIME);
            if (beginTime != null) {
                log.info("host: {}",exchange.getRequest().getURI().getHost());
                log.info("port: {}",exchange.getRequest().getURI().getPort());
                log.info("URL: {}",exchange.getRequest().getURI().getPath());
                log.info("URI param: {}",exchange.getRequest().getURI().getRawQuery());
                log.info("take time: {} ms", System.currentTimeMillis() - beginTime);
                System.out.println();
            }
        }));
    }

    // lower value, higher priority
    @Override
    public int getOrder() {
        return 0;
    }
}
