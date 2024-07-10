package com.frank.redis_study.demo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("123");

        // 類似 Jdbc 一樣，訪問 Redis
        System.out.println(jedis.ping());

        // keys
        System.out.println("============================================");
        System.out.println(jedis.keys("*"));

        // string
        System.out.println("======================string======================");
        System.out.println(jedis.get("k1"));
        jedis.setex("k3", 10L, "v3");
        jedis.set("k4", "v4");
        System.out.println(jedis.ttl("k4"));
        jedis.expire("k4", 20L);

        // list
        System.out.println("======================list======================");
//        jedis.lpush("list", "11", "22", "33");
        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list);

        // set
        System.out.println("======================set======================");
        jedis.sadd("user", "user1");
        jedis.sadd("user", "user2");
        jedis.sadd("user", "user3");
        Set<String> users = jedis.smembers("user");
        System.out.println(users);
        jedis.srem("user", "user2");
        System.out.println(jedis.smembers("user").size());

        // hash
        System.out.println("=======================hash=====================");
        jedis.hset("user1", "id", "1");
        jedis.hset("user1", "name", "frank");
        System.out.println(jedis.hget("user1", "id"));
        System.out.println(jedis.hget("user1", "name"));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "jack");
        jedis.hset("user2", map);
        System.out.println(jedis.hmget("user2", "id", "name"));

        // zset
        jedis.zadd("zset1", 10L, "v1");
        jedis.zadd("zset1", 20L, "v2");
        jedis.zadd("zset1", 40L, "v3");
        System.out.println(jedis.zrange("zset1", 0, -1));

        jedis.close();
    }
}
