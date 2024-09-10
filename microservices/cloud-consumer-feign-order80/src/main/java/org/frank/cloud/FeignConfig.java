package org.frank.cloud;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {
//         return Retryer.NEVER_RETRY; // default never retry

        // maxAttempts=3, initial delay=100ms, period=1s
        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
