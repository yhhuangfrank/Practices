package com.frank.mytest.test.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 假定 A-Z 字母可被編碼成 "1", "2"... "26"
 * 給定一個編碼過後的字串，問共有多少種解碼方式
 */
public class DecodeWays {
    public static void main(String[] args) {
        String s = "20419";
        System.out.println(numDecodings(s));
    }

    /**
     * 分析：
     * 字串 0 ~ i 的 decode 種類與 0 ~ i-1 和 0 ~ i-2 子字串有關
     */
    public static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1]; // 長度 n 的字串共有幾種 decode 方式
        dp[0] = 1; // 沒辦法 decode
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // 長度一的字串考量是否為 0
        for (int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i)); // s[i-1] 位置
            int second = Integer.parseInt(s.substring(i - 2, i)); // s[i-2] 到 s[i-1] 兩位數
            if (first != 0) { // 代表 s[i-1]、s[i]位置可以獨立考慮，dp[i] 的可能性加上 dp[i-1]
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) { // 代表前面兩個字串可以組合
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
