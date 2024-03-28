package com.frank.mytest.test.leetcode.dp;

/**
 * 有一 m x n 的棋盤，左上角為起點，右下角為終點。
 * 從起點出發，每次只能往右走或往下走，問共有多少種不重複的走法
 */
public class UniquePath {
    public static void main(String[] args) {
        System.out.printf("共有 %d 種方法", uniquePathSolution(7, 3));
    }

    /**
     * 走到每一格的走法： 走到此格上一格的方法數 + 走到此格左邊一格的方法數
     * 在邊界的格子 (row = 0 or col = 0) 的方法數皆為 1
     * 因此使用 dynamic programming 推導到終點即為答案
     */
    public static int uniquePathSolution(int m, int n) {// row x col
        int[][] dp = new int[m][n];
        // 1) 將邊界 (row = 0 和 col = 0) 值設為 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 第一行
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1; // 第一列
        }
        // 2) 從第二列第二行開始推導
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1]; // 返回終點的計算結果
    }

    // 使用排列組合解法，此題相當於排列 (向右的 n 個箭頭) 與 (向下的 m 個箭頭) 的不重複排列數
    // ex: 10! / (7! * 3!) = 10x9x8 / 1x2x3 =
}
