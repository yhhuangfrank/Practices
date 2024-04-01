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
        String s = "110";
        System.out.println(numDecodings(s));
    }

    /**
     * 分析：
     * 若字串110 : 對於 0 位置的可能性會與前兩個位置有關( 0 一定要跟前一個組合，最多兩位數)
     * 0 -> 0 無法自己一種
     * 10 -> 1,1 、 10
     * 110 -> 無法拆成110 (無法自己成立), 1,10
     * <p>
     * 若字串101 :
     * 1 -> 1 可以自己一種
     * 10 -> 1,0 、 10 兩種
     * 101 -> 10 (前提為 10 ~ 26 之間)、1
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
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first != 0) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
