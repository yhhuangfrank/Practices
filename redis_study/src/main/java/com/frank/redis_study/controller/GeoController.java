package com.frank.redis_study.controller;

import com.frank.redis_study.service.GeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "地理位置推送")
public class GeoController {

    private final GeoService geoService;

    @ApiOperation("添加座標")
    @GetMapping("/geoadd")
    public String geoAdd() {
        return geoService.geoAdd();
    }

    @ApiOperation("獲取經緯度座標")
    @GetMapping("/geopos")
    public Point geoPos(String member) {
        return geoService.geoPos(member);
    }

    @ApiOperation("獲取經緯度生成的 base32 編碼值 geohash")
    @GetMapping("/geohash")
    public String geoHash(String member) {
        return geoService.geoHash(member);
    }

    @ApiOperation("獲取兩給定位置之間距離")
    @GetMapping("/geodist")
    public Distance geoDist(String member1, String member2) {
        return geoService.geoDist(member1, member2);
    }

    @ApiOperation("透過經緯度獲取其他位置的資訊")
    @GetMapping("/georadius")
    public GeoResults geoRadius() {
        return geoService.geoRadius();
    }

    @ApiOperation("透過已知地點取得一定距離內的其他位置")
    @GetMapping("/georadiusBymemeber")
    public GeoResults geoRadiusByMember() {
        return geoService.geoRadiusByMember();
    }

}
