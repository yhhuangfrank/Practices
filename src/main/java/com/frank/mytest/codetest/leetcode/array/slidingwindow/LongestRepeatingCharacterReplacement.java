package com.frank.mytest.codetest.leetcode.array.slidingwindow;

import java.util.*;

/**
 * 給定一只包含大寫英文字母字串 s 和一整數 k，可將 s 內的任意字母更換為另一別的字母 k 次
 * 問最長的相同字母字串的長度為何？
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * <p>
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k));
        System.out.println(characterReplacementV2(s, k));
    }

    // 更優解：因共有 26 字母需記，直接使用 array 記數量，效率更高
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int l = 0;
        int r = 0;
        int maxLen = 0;
        // O(26n + n) -> O(n) time (r 走 n 次, l 走 26n 次)
        while (r < s.length()) {
            count[s.charAt(r) - 'A'] += 1;
            // find max occurrence of char
            int currStringLen = r - l + 1;
            while (currStringLen - Arrays.stream(count).max().getAsInt() > k) { // 找 max 也可使用 stream api
                count[s.charAt(l) - 'A'] -= 1;
                l++; // 將 l 前進直到再次是合格的區間
                currStringLen--;
            }
            maxLen = Math.max(maxLen, currStringLen);
            r++;
        }
        return maxLen;
    }

    public static int findMax(int[] count) { // O(26)
        int max = 0;
        for (int c : count) {
            max = Math.max(max, c);
        }
        return max;
    }

    public static int characterReplacementV2(String s, int k) {
        Map<String, Integer> count = new HashMap<>();
        int l = 0;
        int r = 0;
        int maxLen = 0;
        // O(26n + n) -> O(n) time (r 走 n 次, l 走 26n 次)
        while (r < s.length()) {
            String c = s.substring(r, r + 1);
            count.put(c, count.getOrDefault(c, 0) + 1);
            // find max occurrence of char
            int currStringLen = r - l + 1;
            while (currStringLen - findMax(count) > k) {
                String ch = s.substring(l, l + 1);
                count.put(ch, count.get(ch) - 1);
                l++;
                currStringLen--;
            }
            maxLen = Math.max(maxLen, currStringLen);
            r++;
        }
        return maxLen;
    }

    public static int findMax(Map<String, Integer> count) { // O(26)
        int max = 0;
        for (Integer v : count.values()) {
            max = Math.max(max, v);
        }
        return max;
    }
}
