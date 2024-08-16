package com.frank.redis_redlock.config;

import lombok.Data;


@Data
public class RedisPoolProperties {

    private int maxIdle;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int connTimeout = 10000;

    private int soTimeout;

    private int size;
}
