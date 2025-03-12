package com.frank.mytest;

import com.frank.mytest.codetest.leetcode.linkedlist.LRUCache;
import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void test() {
        String s1 = "s";
        System.out.println(s1 + ":" + null);
    }

    @Test
    public void testLRUCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
