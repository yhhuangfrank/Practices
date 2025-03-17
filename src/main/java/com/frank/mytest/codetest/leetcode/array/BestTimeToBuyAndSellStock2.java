package com.frank.mytest.codetest.leetcode.array;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BestTimeToBuyAndSellStock2 {
//    Input: prices = [7,1,5,3,6,4]
//    Output: 7
//    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//    Total profit is 4 + 3 = 7.

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(sol.maxProfit(prices));
    }

    static class Solution {
        public int maxProfit(int[] prices) {
//            return maxProfitByBruteForce(prices);
//            return maxProfitByTopDownDP(prices);
//            return maxProfitByDP(prices);
//            return maxProfitByOptimizedDP(prices);
            return maxProfitByGreedy(prices);
        }

        private int maxProfitByGreedy(int[] prices) {
            // 每次價格上升都計算價差
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        private int maxProfitByOptimizedDP(int[] prices) {
            int[] dp = new int[2];
            for (int i = prices.length - 1; i >= 0; i--) {
                int[] newDP = new int[2];
                for (int j = 1; j >= 0; j--) {
                    int skip = dp[j];
                    if (j == 1) {
                        int buy = -prices[i] + dp[0];
                        newDP[j] = Math.max(skip, buy);
                    } else {
                        int sell = prices[i] + dp[1];
                        newDP[j] = Math.max(skip, sell);
                    }
                }
                dp = newDP;
            }
            return dp[1];
        }

        private int maxProfitByDP(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n + 1][2];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 1; j >= 0; j--) {
                    int skip = dp[i + 1][j];
                    if (j == 1) {
                        int buy = -prices[i] + dp[i + 1][0];
                        dp[i][j] = Math.max(skip, buy);
                    } else {
                        int sell = prices[i] + dp[i + 1][1];
                        dp[i][j] = Math.max(skip, sell);
                    }
                }
            }
            return dp[0][1];
        }

        private int maxProfitByTopDownDP(int[] prices) {
            return dfsV2(0, 1, prices, new HashMap<>());
        }

        private int dfsV2(int i, int isSold, int[] prices, Map<Pair, Integer> cache) {
            Pair k = new Pair(i, isSold);
            if (cache.containsKey(k)) return cache.get(k);
            if (i == prices.length) return 0;
            int skip = dfsV2(i + 1, isSold, prices, cache);
            int res;
            if (isSold == 1) {
                int buy = -prices[i] + dfsV2(i + 1, 0, prices, cache);
                res = Math.max(skip, buy);
            } else {
                int sell = prices[i] + dfsV2(i + 1, 1, prices, cache);
                res = Math.max(sell, skip);
            }
            cache.put(k, res);
            return res;
        }

        public int maxProfitByBruteForce(int[] prices) {
            return dfsV1(0, 1, prices);
        }

        private int dfsV1(int i, int isSold, int[] prices) {
            if (i == prices.length) return 0;
            int skip = dfsV1(i + 1, isSold, prices);
            if (isSold == 1) {
                int buy = -prices[i] + dfsV1(i + 1, 0, prices);
                return Math.max(buy, skip);
            } else {
                int sell = prices[i] + dfsV1(i + 1, 1, prices);
                return Math.max(sell, skip);
            }
        }

        @AllArgsConstructor
        private static class Pair {
            private final int i;
            private final int j;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o instanceof Pair p) return this.i == p.i && this.j == p.j;
                return false;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.i, this.j);
            }
        }
    }
}
