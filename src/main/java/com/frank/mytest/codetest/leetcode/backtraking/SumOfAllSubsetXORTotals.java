package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.*;

public class SumOfAllSubsetXORTotals {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsetXORSum(new int[]{1, 3})); // 6
        System.out.println(solution.subsetXORSum(new int[]{5, 1, 6})); // 28
    }

    static class Solution {
        public int subsetXORSum(int[] nums) {
            return backtrack(0, new ArrayDeque<>(), nums);
        }

        private int backtrack(int i, Deque<Integer> arr, int[] nums) {
            if (i == nums.length) return calculate(arr);
            int res = 0;
            arr.addLast(nums[i]);
            res += backtrack(i + 1, arr, nums);
            arr.pollLast();
            res += backtrack(i + 1, arr, nums);
            return res;
        }

        private int calculate(Deque<Integer> arr) {
            int res = 0;
            for (int n : arr) {
                res ^= n;
            }
            return res;
        }
    }
}
