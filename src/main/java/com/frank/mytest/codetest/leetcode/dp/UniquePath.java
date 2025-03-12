package com.frank.mytest.codetest.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 有一 m x n 的棋盤，左上角為起點，右下角為終點。
 * 從起點出發，每次只能往右走或往下走，問共有多少種不重複的走法
 */
public class UniquePath {
    public static void main(String[] args) {
        System.out.printf("共有 %d 種方法", uniquePathSolution(7, 3));
    } // 28

    /**
     * 走到每一格的走法： 走到此格上一格的方法數 + 走到此格左邊一格的方法數
     * 在邊界的格子 (row = 0 or col = 0) 的方法數皆為 1
     * 因此使用 dynamic programming 推導到終點即為答案
     */
    public static int uniquePathSolutionByDP(int m, int n) {// row x col
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
    // ex: (m + n - 2)! / (m - 1)! * (n - 1)! = 8! / (6! * 2!) = 8x7 / 1x2 = 28

    public static int uniquePathSolution(int m, int n) {
//        return uniquePathByBruteForce(m, n);
//        return uniquePathByTopDownDP(m, n);
//        return uniquePathSolutionByDP(m, n);
//        return uniquePathSolutionByDPV2(m,n);
        return uniquePathSolutionByDPV3(m,n);
    }

    private static int uniquePathSolutionByDPV3(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = m - 2; i >= 0; i--) {
            int[] newDp = new int[n];
            newDp[n - 1] = 1;
            for (int j = n - 2; j >= 0; j--) {
                newDp[j] = dp[j] + newDp[j + 1];
            }
            dp = newDp;
        }
        return dp[0];
    }

    // DP solution
    private static int uniquePathSolutionByDPV2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    // memoization
    private static int uniquePathByTopDownDP(int m, int n) {
        return dfsTopDownDP(0, 0, m, n, new HashMap<>());
    }

    private static int dfsTopDownDP(int i, int j, int m, int n, Map<Pair, Integer> cache) {
        if (Math.min(i, j) < 0 || i == m || j == n) return 0;
        Pair k = new Pair(i, j);
        if (cache.containsKey(k)) return cache.get(k);
        if (i == m - 1 && j == n - 1) return 1;
        int res = dfsTopDownDP(i + 1, j, m, n, cache) + dfsTopDownDP(i, j + 1, m, n, cache);
        cache.put(k, res);
        return res;
    }

    // brute force
    public static int uniquePathByBruteForce(int m, int n) {
        return dfs(0, 0, m, n);
    }

    public static int dfs(int i, int j, int m, int n) {
        if (Math.min(i, j) < 0 || i == m || j == n) return 0;
        if (i == m - 1 && j == n - 1) return 1;
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}
