package com.frank.mytest.codetest.leetcode.heap;

import java.util.PriorityQueue;

public class TotalCostToHiringKWorkers {
    //    Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
//    Output: 11
//    Input: costs = [1,2,4,1], k = 3, candidates = 3
//    Output: 4
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8};
        int[] arr2 = new int[]{1, 2, 4, 1};
        System.out.println(sol.totalCost(arr1, 3, 4)); // 11
        System.out.println(sol.totalCost(arr2, 3, 3)); // 4
    }

    static class Solution {
        public long totalCost(int[] costs, int k, int candidates) {
            PriorityQueue<Integer> left = new PriorityQueue<>();
            PriorityQueue<Integer> right = new PriorityQueue<>();
            int count = 0;
            int l = 0;
            int r = costs.length - 1;
            long res = 0L;
            while (count < k) {
                while (l <= r && left.size() < candidates) {
                    left.add(costs[l]);
                    l += 1;
                }
                while (l <= r && right.size() < candidates) {
                    right.add(costs[r]);
                    r -= 1;
                }
                int i1 = !left.isEmpty() ? left.peek() : Integer.MAX_VALUE;
                int i2 = !right.isEmpty() ? right.peek() : Integer.MAX_VALUE;
                if (i1 <= i2) {
                    res += i1;
                    left.poll();
                } else {
                    res += i2;
                    right.poll();
                }
                count += 1;
            }
            return res;
        }
    }
}
