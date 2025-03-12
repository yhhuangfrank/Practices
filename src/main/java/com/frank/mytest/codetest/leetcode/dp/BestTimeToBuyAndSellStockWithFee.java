package com.frank.mytest.codetest.leetcode.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BestTimeToBuyAndSellStockWithFee {
//    Input: prices = [1,3,2,8,4,9], fee = 2
//    Output: 8
//    Input: prices = [1,3,7,5,10,3], fee = 3
//    Output: 6

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
        System.out.println(sol.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
    }

    static class Solution {
        public int maxProfit(int[] prices, int fee) {
//            return maxProfitByBruteForce(prices, fee);
//            return maxProfitByMemoization(prices, fee);
//            return maxProfitByDP(prices, fee);
            return maxProfitByDPV2(prices, fee);
        }

        // memory optimized dp
        private int maxProfitByDPV2(int[] prices, int fee) {
            int n = prices.length;
            int[] dp = new int[2]; // 0: not sold ; 1: sold
            for (int i = n - 1; i >= 0; i--) {
                int[] newDP = new int[2];
                newDP[0] = Math.max(dp[0], prices[i] - fee + dp[1]); // skip or sell
                newDP[1] = Math.max(dp[1], -prices[i] + dp[0]); // skip or buy
                dp = newDP;
            }
            return dp[1];
        }

        // Dynamic Programming
        private int maxProfitByDP(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp = new int[n + 1][2];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 1; j >= 0; j--) {
                    int res = 0;
                    int skip = dp[i + 1][j];
                    if (j == 1) {
                        int buy = -prices[i] + dp[i + 1][0];
                        res += Math.max(buy, skip);
                    } else {
                        int sell = prices[i] - fee + dp[i + 1][1];
                        res += Math.max(sell, skip);
                    }
                    dp[i][j] = res;
                }
            }
            return dp[0][1];
        }

        // memoization
        private int maxProfitByMemoization(int[] prices, int fee) {
            return dfs2(0, 1, prices, fee, new HashMap<>());
        }

        private int dfs2(int i, int isSold, int[] prices, int fee, Map<Pair, Integer> cache) {
            if (i == prices.length) return 0;
            Pair k = new Pair(i, isSold);
            if (cache.containsKey(k)) return cache.get(k);
            int res = 0;
            int skip = dfs2(i + 1, isSold, prices, fee, cache);
            if (isSold == 1) {
                int buy = -prices[i] + dfs2(i + 1, 0, prices, fee, cache);
                res += Math.max(buy, skip);
            } else {
                int sell = prices[i] - fee + dfs2(i + 1, 1, prices, fee, cache);
                res += Math.max(sell, skip);
            }
            cache.put(k, res);
            return res;
        }

        // brute force
        private int maxProfitByBruteForce(int[] prices, int fee) {
            return dfs1(0, 1, prices, fee);
        }

        private int dfs1(int i, int isSold, int[] prices, int fee) {
            if (i == prices.length) return 0;
            int res = 0;
            int skip = dfs1(i + 1, isSold, prices, fee);
            if (isSold == 1) {
                int buy = -prices[i] + dfs1(i + 1, 0, prices, fee);
                res += Math.max(skip, buy);
            } else {
                int sell = prices[i] - fee + dfs1(i + 1, 1, prices, fee);
                res += Math.max(skip, sell);
            }
            return res;
        }

        private class Pair {
            private int i;
            private int j;

            public Pair(int i, int j) {
                this.i = i;
                this.j = j;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || this.getClass() != obj.getClass()) return false;
                Pair p = (Pair) obj;
                return this.i == p.i && this.j == p.j;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.i, this.j);
            }
        }
    }
}
