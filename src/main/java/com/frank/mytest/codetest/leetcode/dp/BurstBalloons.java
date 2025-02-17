package com.frank.mytest.codetest.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class BurstBalloons {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(new int[]{3, 1, 5, 8}));
    }

}

class Solution {

    // Top-Down DP, O(n^3) time
    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        int i = 1;
        for (int n : nums) {
            arr[i] = n;
            i += 1;
        }
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for (int j = 0; j < dp.length; j++) {
            for (int k = 0; k < dp[0].length; k++) {
                dp[j][k] = -1;
            }
        }

        return dfs(1, arr.length - 2, arr, dp);
    }

    private int dfs(int l, int r, int[] arr, int[][] dp) {
        if (l > r) return 0;
        if (dp[l][r] != -1) return dp[l][r];
        int maxC = 0;
        for (int i = l; i < r + 1; i++) {
            int coins = arr[i] * arr[l - 1] * arr[r + 1];
            coins += dfs(l, i - 1, arr, dp) + dfs(i + 1, r, arr, dp);
            maxC = Math.max(maxC, coins);
        }
        dp[l][r] = maxC;
        return dp[l][r];
    }
}
