package com.frank.redis_study.service;

import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoService {

    public static final String CITY = "city";

    private final RedisTemplate<String, Object> redisTemplate;

    public String geoAdd() {
        Map<Object, Point> map = new HashMap<>();
        map.put("Taipei", new Point(121.52054973617487, 25.043922687648088));// 先經度，再緯度
        map.put("大巨蛋", new Point(121.56140464768542, 25.04285785802034));
        map.put("101", new Point(121.56486885885839, 25.03350058295409));

        redisTemplate.opsForGeo().add(CITY, map);

        return map.toString();
    }

    public Point geoPos(String member) {
        List<Point> positions = redisTemplate.opsForGeo().position(CITY, member);
        return positions != null ? positions.get(0) : null;
    }

    public String geoHash(String member) {
        List<String> hashes = redisTemplate.opsForGeo().hash(CITY, member);
        return hashes != null ? hashes.get(0) : null;
    }

    public Distance geoDist(String member1, String member2) {
        return redisTemplate.opsForGeo().distance(CITY, member1, member2, Metrics.KILOMETERS); // or RedisGeoCommands.DistanceUnit.KILOMETERS
    }

    public GeoResults geoRadius() {
        Circle circle = new Circle(121.52054973617487, 25.043922687648088, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeCoordinates()
                .includeDistance()
                .sort(Sort.Direction.DESC)
                .limit(50);
        return redisTemplate.opsForGeo().radius(CITY, circle, args);
    }

    public GeoResults geoRadiusByMember() {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(10);
        Distance distance = new Distance(10, Metrics.KILOMETERS);
        return redisTemplate.opsForGeo().radius(CITY, "Taipei", distance, args);
    }
}
