package com.frank.mytest.codetest.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

// 根據需求返回具體網站
public class WebSiteFactory {

    // 集合，充當池的作用
    private Map<String, ConcreteWebSite> pool = new HashMap<>();

    public WebSite getWebSiteCategory(String type) {
        pool.computeIfAbsent(type, ConcreteWebSite::new); // 檢查type是否存在，若沒有則創建一個
        return pool.get(type);
    }

    public int getWebSiteCategoryCount() {
        return pool.size();
    }
}
