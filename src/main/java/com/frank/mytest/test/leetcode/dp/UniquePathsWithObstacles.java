package com.frank.mytest.test.leetcode.dp;

/**
 * 有一 m x n 的棋盤，左上角為起點，右下角為終點。
 * 從起點出發，每次只能往右走或往下走，問共有多少種不重複的走法
 * 並考慮中間可能有設置障礙物
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] obstacleGrid = new int[m][n];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (i == 1 && j == 1) {
                    obstacleGrid[i][j] = 1; // 3x3 棋盤正中心有障礙物
                }
            }
        }
        System.out.printf("共有 %d 種方法", uniquePathsWithObstaclesSolution(obstacleGrid));
    }

    /**
     * 走到每一格的走法： 走到此格上一格的方法數 + 走到此格左邊一格的方法數
     * 在可能有障礙物狀況下，能確定的初始條件只有起點 (一種走法)
     * 因此使用 dynamic programming 推導到終點即為答案
     */
    public static int uniquePathsWithObstaclesSolution(int[][] obstacleGrid) {// 傳入二維陣列，用 01 標記是否有障礙物
        if (obstacleGrid[0][0] == 1) return 0; // 若起點有障礙物，則都不能走

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];
        // 1) 將起點值設為 1
        dp[0][0] = 1;

        // 2) 從第一列開始推導，計算時先考慮 i - 1 和 j -1 位置上有無障礙物，若有則不列入計算
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1 || (i == 0 && j == 0)) {
                    // 障礙物不用計算，保持預設的 0，起點則已經賦值為 1
                    continue;
                }
                if (i - 1 >= 0) { // 確保不越界
                    dp[i][j] += dp[i - 1][j]; // 加上上方步數
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1]; // 加上左方步數
                }
            }
        }
        return dp[rows - 1][cols - 1]; // 返回終點的計算結果
    }
}
