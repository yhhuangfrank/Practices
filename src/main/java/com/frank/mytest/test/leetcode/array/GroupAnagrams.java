package com.frank.mytest.test.leetcode.array;

import java.util.*;

/**
 * 給定一串 string array，將彼此是 anagram 的文字組成一個個 list 返回
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = new String[] {"bdddddddddd","bbbbbbbbbbc"};
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagramsV2(strs));
        System.out.println(groupAnagramsV2(strs2));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>(Arrays.asList(strs));

        while (!temp.isEmpty()) {
            List<String> list = new ArrayList<>();
            List<String> remained = new ArrayList<>();

            for (String s : temp) {
                if (list.isEmpty()) {
                    list.add(s);
                    continue;
                }
                String first = list.get(0);
                if (isAnagram(first, s)) {
                    list.add(s);
                } else {
                    remained.add(s);
                }
            }
            res.add(list);
            temp = remained;
        }
        return res;
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i) - 'a';
            arr[index] += 1;
        }
        for (int i = 0; i < s2.length(); i++) {
            int index = s2.charAt(i) - 'a';
            if (arr[index] == 0) return false;
            arr[index] -= 1;
        }
        for (int n : arr) {
            if (n > 0) return false;
        }
        return true;
    }

    // Time Complexity O(m * n) m : string.length, n : strs.length
    public static List<List<String>> groupAnagramsV2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";

        for (String s : strs) {
            int[] count = new int[26]; // count letters
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                count[c - 'a'] += 1;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    sb.append(count[i]);
                    sb.append(letters.charAt(i));
                }
            }
            String key = sb.toString();
            List<String> list = map.get(key) != null ? map.get(key) : new ArrayList<>();
            list.add(s);
            map.put(key,list);
        }

        map.forEach((k, v) -> res.add(v));
        return res;
    }

}
