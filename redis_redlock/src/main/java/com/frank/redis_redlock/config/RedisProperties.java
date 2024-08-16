package com.frank.redis_redlock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
public class RedisProperties {

    private int database;

    /**
     * 等待節點回覆命令的時間，從命令發送成功時開始計時
     */
    private int timeout = 3000;

    private String password;

    private String mode;

    private RedisPoolProperties pool;

    private RedisSingleProperties single;
}
