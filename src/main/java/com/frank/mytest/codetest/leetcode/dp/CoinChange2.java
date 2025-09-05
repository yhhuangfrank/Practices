package com.frank.mytest.codetest.leetcode.dp;

public class CoinChange2 {

    private static class Solution {

        public int coinChange(int[] coins, int amount) {
//            return coinChangeBruteForce(coins, amount);
            return coinChangeDP(coins, amount);
        }

        public int coinChangeBruteForce(int[] coins, int amount) {
            return bruteForceDFS(0, amount, coins);
        }

        private int bruteForceDFS(int i, int remain, int[] coins) {
            if (i == coins.length || remain < 0) return 0;
            if (remain == 0) return 1;

            int res = 0;
            if (remain - coins[i] >= 0) {
                res += bruteForceDFS(i + 1, remain, coins);
                res += bruteForceDFS(i, remain - coins[i], coins);
            }
            return res;
        }

        // time complexity: O(coins.length * amount)
        public int coinChangeDP(int[] coins, int amount) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 1;
            }

            for (int i = coins.length - 1; i >= 0; i--) {
                for (int j = 0; j < amount + 1; j++) {
                    if (j - coins[i] >= 0) {
                        dp[i][j] += dp[i + 1][j];
                        dp[i][j] += dp[i][j - coins[i]];
                    }
                }
            }

            return dp[0][amount];
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.coinChange(new int[]{1, 2, 5}, 5)); // 4
        System.out.println(sol.coinChange(new int[]{2}, 3)); // 0
        System.out.println(sol.coinChange(new int[]{10}, 10)); // 1
    }
}
