package org.frank.cloud.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
            return (userType != null && userType.equalsIgnoreCase(config.getUserType()));
        };
    }

    public static class Config {

        @Getter
        @Setter
        private String userType;

        public Config() {
        }
    }
}
