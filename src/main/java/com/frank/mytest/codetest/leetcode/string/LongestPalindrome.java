package com.frank.mytest.codetest.leetcode.string;


import java.util.HashMap;
import java.util.Map;

/**
 * 給定一個包含大小寫字母的字串，找出能組出的最長"回文"，並回傳其長度
 * 大小寫有區分, ex : "Aa" 不能視為回文
 * <p>
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * <p>
 * Example 2:
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s1 = "abccccdd";
        String s2 = "ccc";
        String s3 = "aA";
        System.out.println(longestPalindromeWithMap(s1));
        System.out.println(longestPalindromeWithMap(s2));
        System.out.println(longestPalindromeWithMap(s3));
        System.out.println("=============================");
        System.out.println(longestPalindromeWithBuckets(s1));
        System.out.println(longestPalindromeWithBuckets(s2));
        System.out.println(longestPalindromeWithBuckets(s3));
    }

    /**
     * 回文組合條件
     * 1) 字元兩兩成對
     * 2) 允許一個例外是中間的字母
     * <p>
     * 想法：
     * 1. 記住每個大小寫出現的次數 (可使用桶或 hash table)
     * 2. 所有成對的都可以列入計算
     * 3. 最後查看有無機會放入單一字元
     */
    public static int longestPalindromeWithMap(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            countMap.merge(s.charAt(i), 1, (oldValue, value) -> oldValue + value);
        }

        int len = 0;
        boolean isSingleCharExist = false;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            int val = entry.getValue();
            if (val % 2 == 0) {
                len += 2 * (val >> 1);
            } else if (val % 2 == 1) { // 奇數也可倆倆配對後有一個單一元素
                len += 2 * (val >> 1);
                isSingleCharExist = true;
            }
        }
        if (isSingleCharExist) {
            len++;
        }
        return len;
    }

    public static int longestPalindromeWithBuckets(String s) {
        int[] buckets = new int[128]; // 宣告 ascii code a-z 和 A-Z 會包含的範圍
        for (int i = 0; i < s.length(); i++) {
            int index = s.codePointAt(i);
            buckets[index] += 1;
        }
        // 計算 buckets 上不為 0 的元素可組出的最長回文總長
        int len = 0;
        boolean isSingleCharExist = false;
        for (int b : buckets) {
            if (b == 0) continue;
            if (b % 2 == 0) {
                len += 2 * (b / 2);
            } else if (b % 2 == 1) {
                len += 2 * (b / 2);
                isSingleCharExist = true;
            }
        }
        if (isSingleCharExist) {
            len++;
        }
        return len;
    }

}
