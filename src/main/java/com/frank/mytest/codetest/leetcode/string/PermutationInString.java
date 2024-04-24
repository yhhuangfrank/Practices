package com.frank.mytest.codetest.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 給定兩字串 s1, s2。判斷 s2 中是否包含 s1 的排列。字串皆為英文字母
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 */
public class PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println(checkInclusionV2(s1, s2));
    }

    /**
     * s2 的 substring 一定要與 s1 的長度相同 (才會是某一種排列)
     * 指定一個 window (長度為 s1.length)，擷取 s2 的字串出來，確認字母個數是否一致
     * 若沒找到則往右移動一個單位，直到遍歷完整個 s2 字串
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.isEmpty() && s2.isEmpty()) return true;
        if (s1.isEmpty() || s2.isEmpty() || s1.length() > s2.length()) return false;

        int l = 0;
        int r = l + s1.length() - 1;
        String temp;
        boolean isValid = true;

        while (r < s2.length()) {
            temp = s2.substring(l, r + 1);
            if (temp.equals(s1)) return true;
            // 不相等，檢查字母個數
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < s1.length(); i++) {
                String c = s1.charAt(i) + "";
                map.merge(c, 1, Integer::sum);
            }
            for (int i = 0; i < temp.length(); i++) {
                String c = temp.charAt(i) + "";
                Integer val = map.get(c);
                if (val == null || val == 0) {
                    isValid = false;
                    break;
                }
                map.computeIfPresent(c, (s, value) -> value - 1);
            }

            if (isValid) return true;
            l++;
            r++;
            isValid = true;
        }

        return false;
    }

    /**
     * 想法：
     * sliding window ，讓 r 指針先往前走，走到 s1 長度後，若沒有找到結果
     * l 指針就開始往前。並且 r,l 開始保持著 s1 長度的距離一直到走完 s2 字串
     */
    public static boolean checkInclusionV2(String s1, String s2) {
        if (s1.isEmpty() && s2.isEmpty()) return true;
        if (s1.isEmpty() || s2.isEmpty() || s1.length() > s2.length()) return false;

        int l = 0;
        int r = 0;
        int count = s1.length(); // 需要比對的字串總長度
        Map<Character, Integer> map = new HashMap<>(); // 紀錄 s1 元素個數
        for (int i = 0; i < s1.length(); i++) {
            map.merge(s1.charAt(i), 1, Integer::sum);
        }
        while (r < s2.length()) {
            Integer value = map.get(s2.charAt(r));
            if (value != null && value > 0) {
                count--; // 需要比對的字母少一個
            }
            map.computeIfPresent(s2.charAt(r), (c, num) -> num - 1); // 比對到屬於 s1 中字母的扣除其個數
            r++;
            if (count == 0) return true; // 代表找到目前 l 到 r 這段的 substring 是 s1 的一種排列
            if (r - l == s1.length()) { // l,r 相距 s1 長度，但沒有找到，左指針需往前移動
                value = map.get(s2.charAt(l));
                if (value != null && value >= 0) {
                    count++; // 需重新比對，因此把需比對的字母數加回
                }
                map.computeIfPresent(s2.charAt(l), (c, num) -> num + 1); // 將扣除過的個數加回
                l++;
            }
        }
        return false;
    }
}