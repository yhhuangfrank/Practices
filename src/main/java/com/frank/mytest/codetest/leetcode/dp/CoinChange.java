package com.frank.mytest.codetest.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    public static class Solution {

        public int coinChange(int[] coins, int amount) {
//            return bruteForce(coins, amount);
//            return topDownDP(coins, amount);
            return bottomUpDP(coins, amount);
        }

        private int bottomUpDP(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            dp[0] = 0;

            for (int i = 1; i <= amount; i++) {
                int res = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        int prevMin = dp[i - coin] == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + dp[i - coin];
                        res = Math.min(res, prevMin);
                    }
                }
                dp[i] = res;
            }

            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }

        private int bruteForce(int[] coins, int amount) {
            int minCoin = bruteForceDFS(coins, amount);
            return minCoin == Integer.MAX_VALUE ? -1 : minCoin;
        }

        private int bruteForceDFS(int[] coins, int amount) {
            if (amount == 0) return 0;

            int minCoin = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (amount - coin >= 0) {
                    int cnt = bruteForceDFS(coins, amount - coin);
                    minCoin = Math.min(minCoin, cnt == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + cnt);
                }
            }

            return minCoin;
        }

        private int topDownDP(int[] coins, int amount) {
            Map<Integer, Integer> cache = new HashMap<>();
            cache.put(0, 0);
            int minCoin = topDownDPDFS(coins, amount, cache);
            return minCoin == Integer.MAX_VALUE ? -1 : minCoin;
        }

        private int topDownDPDFS(int[] coins, int amount, Map<Integer, Integer> cache) {
            if (cache.containsKey(amount)) return cache.get(amount);

            int minCoin = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (amount - coin >= 0) {
                    int cnt = topDownDPDFS(coins, amount - coin, cache);
                    minCoin = Math.min(minCoin, cnt == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + cnt);
                }
            }

            cache.put(amount, minCoin);
            return minCoin;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(solution.coinChange(new int[]{2}, 3)); // -1
        System.out.println(solution.coinChange(new int[]{1}, 0)); // 0
    }
}
