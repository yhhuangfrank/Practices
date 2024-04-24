package com.frank.mytest.codetest.leetcode.dp;

/**
 * 給定一 m x n 的表格，每個位置上有一非負整數，每次往右或往下移動
 * 從最左下角走到最右下角的路線中，所經數字總和最小為何？
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[3][3];
        grid[0] = new int[]{1, 3, 1};
        grid[1] = new int[]{1, 5, 1};
        grid[2] = new int[]{4, 2, 1};
        System.out.println(minPathSum(grid));
    }

    /**
     * 類似 unique path 解法，考慮
     * 1) 來自左邊的可能性
     * 2) 來自上方的可能性
     * <p>
     * 第一列與第一行都只有一條路走，因此最小的和即為目前 grid[i][j] + 前一格
     * 第一列： grid[0][j] = grid[0][j-1] + grid[0][j-2] + ... grid[0][0]
     * 第一行： grid[i][0] = grid[i-1][0] + grid[i-2][0] + ... grid[0][0]
     * 其他格子：考慮左邊的與上面的，比較小的總和做疊加
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // 第一列
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 第一行
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 其他
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int curr = grid[i][j];
                int up = curr + dp[i - 1][j];
                int left = curr + dp[i][j - 1];
                dp[i][j] = Math.min(up, left);
            }
        }

        return dp[m - 1][n - 1];
    }
}
