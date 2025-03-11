package com.frank.mytest.codetest.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class MinCostClimbingStairs {
//    Input: cost = [10,15,20]
//    Output: 15
//    Explanation: You will start at index 1.
//            - Pay 15 and climb two steps to reach the top.
//    The total cost is 15.
//
//    Input: cost = [1,100,1,1,1,100,1,1,100,1]
//    Output: 6

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(sol.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
//            return minCostClimbingStairsV1(cost);
//            return minCostClimbingStairsV2(cost, new HashMap<>());
            return minCostClimbingStairsV3(cost);
        }

        private int minCostClimbingStairsV3(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n + 2];
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
            }
            return Math.min(dp[0], dp[1]);
        }

        // memoization
        private int minCostClimbingStairsV2(int[] cost, Map<Integer, Integer> cache) {
            return Math.min(dfsV2(0, cost, cache), dfsV2(1, cost, cache));
        }

        private int dfsV2(int i, int[] cost, Map<Integer, Integer> cache) {
            if (cache.containsKey(i)) return cache.get(i);
            if (i >= cost.length) return 0;
            int minCost = cost[i] + Math.min(dfsV2(i + 1, cost, cache), dfsV2(i + 2, cost, cache));
            cache.put(i, minCost);
            return minCost;
        }

        // brute force
        private int minCostClimbingStairsV1(int[] cost) {
            return Math.min(dfsV1(0, cost), dfsV1(1, cost));
        }

        private int dfsV1(int i, int[] cost) {
            if (i >= cost.length) return 0;
            return cost[i] + Math.min(dfsV1(i + 1, cost), dfsV1(i + 2, cost));
        }
    }
}
