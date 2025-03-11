package com.frank.mytest.codetest.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class NthTribonacciNumber {
//    The Tribonacci sequence Tn is defined as follows:
//    T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
//    Given n, return the value of Tn.
//
//    Example 1:
//    Input: n = 4
//    Output: 4
//
//    Example 2:
//    Input: n = 25
//    Output: 1389537

    public static void main(String[] args) {
        // 0 <= n <= 37
        Solution solution = new Solution();
        System.out.println(solution.tribonacci(4));
        System.out.println(solution.tribonacci(25));
    }

    static class Solution {
        public int tribonacci(int n) {
//            return tribonacciV1(n);
//            return tribonacciV2(n, new HashMap<>());
            return tribonacciV3(n);
        }

        // bottom up
        private int tribonacciV3(int n) {
            int[] dp = new int[38];
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            return dp[n];
        }

        //        memoization
        private int tribonacciV2(int n, Map<Integer, Integer> cache) {
            if (cache.containsKey(n)) return cache.get(n);
            if (n == 0) return 0;
            if (n == 1 || n == 2) return 1;
            int res = tribonacciV2(n - 1, cache) + tribonacciV2(n - 2, cache) + tribonacciV2(n - 3, cache);
            cache.put(n, res);
            return res;
        }

        // brute force
        private int tribonacciV1(int n) {
            if (n == 0) return 0;
            if (n == 1 || n == 2) return 1;
            return tribonacciV1(n - 1) + tribonacciV1(n - 2) + tribonacciV1(n - 3);
        }
    }
}
