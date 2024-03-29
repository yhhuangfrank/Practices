package com.frank.mytest.test.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    Map<Character, Object> tree = new HashMap<>();

    public void insert(String word) {
        Map<Character, Object> ite = tree;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            ite.computeIfAbsent(c, k -> new HashMap<Character, Object>());
            ite = (Map<Character, Object>) ite.get(c);
        }
        ite.put('#', '#'); // 到最後一個 map 時，初始化一個結尾符號，代表此文字結束
    }

    public boolean search(String word) {
        Map<Character, Object> ite = tree;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ite.get(c) == null) return false;
            ite = (Map<Character, Object>) ite.get(c);
        }
        return ite.get('#') != null && '#' == (Character) ite.get('#'); // 檢查結尾符號
    }

    public boolean startsWith(String prefix) {
        Map<Character, Object> ite = tree;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ite.get(c) == null) return false;
            ite = (Map<Character, Object>) ite.get(c);
        }
        return true;
    }
}
