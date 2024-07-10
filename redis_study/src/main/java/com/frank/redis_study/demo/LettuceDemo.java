package com.frank.redis_study.demo;

import io.lettuce.core.Range;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.HashMap;

public class LettuceDemo {
    public static void main(String[] args) {

        // 1. 使用 builder 構建 RedisURI
        RedisURI uri = RedisURI.builder()
                .redis("127.0.0.1")
                .withPort(6379)
                .build();

        // 2. 創建連接客戶端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> conn = redisClient.connect();

        // 3. 透過 conn 創建操作的 command
        RedisCommands<String, String> command = conn.sync();
        // keys
        System.out.println("============================================");
        System.out.println(command.keys("*"));

        // string
        System.out.println("======================string======================");
        command.set("k5", "hello lettuce");
        System.out.println(command.get("k5"));

        // list
        System.out.println("======================list======================");
        command.lpush("list2", "v3", "v2", "v1");
        System.out.println(command.lrange("list", 0, -1));

        // set
        System.out.println("======================set======================");
        command.sadd("set2", "v1", "v2", "v3");
        System.out.println(command.smembers("set2"));

        // hash
        System.out.println("======================hash======================");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "3");
        map.put("name", "nanashi");
        command.hset("hash1", map);
        System.out.println(command.hmget("hash1", "id", "name"));

        // zset
        System.out.println("======================zset======================");
        ScoredValue<String> sv1 = ScoredValue.just(10L, "v1");
        ScoredValue<String> sv2 = ScoredValue.just(40L, "v2");
        ScoredValue<String> sv3 = ScoredValue.just(20L, "v3");
        command.zadd("zset2", sv1, sv2, sv3);
        System.out.println(command.zrange("zset2", 0, -1));
        System.out.println(command.zrangebyscore("zset2", Range.create(0L, 100L)));

        // 4. 各種關閉釋放
        conn.close();
        redisClient.shutdown();
    }
}
