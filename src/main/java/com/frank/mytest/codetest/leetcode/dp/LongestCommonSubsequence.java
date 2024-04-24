package com.frank.mytest.codetest.leetcode.dp;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String t1 = "abbcde";
        String t2 = "abcbe";
        System.out.println(longestCommonSubsequence(t1, t2)); // 4
    }

    public static int longestCommonSubsequence(String t1, String t2) {
        int r = t1.length();
        int c = t2.length();
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j ++) {
                if (t1.charAt(i) == t2.charAt(j)) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    int max = 0;
                    if (i - 1 >= 0) {
                        max = dp[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        max = Math.max(max, dp[i][j - 1]);
                    }
                    dp[i][j] = max;
                }
            }
        }
        return dp[r - 1][c - 1];
    }
}
