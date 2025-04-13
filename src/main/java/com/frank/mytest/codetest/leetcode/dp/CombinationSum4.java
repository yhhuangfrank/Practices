package com.frank.mytest.codetest.leetcode.dp;

import java.util.*;

public class CombinationSum4 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = { 1, 2, 3 };
    int target = 4;
    System.out.println(solution.combinationSum4(nums, target)); // 7
  }

  static class Solution {
    public int combinationSum4(int[] nums, int target) {
      // return combinationSum4ByBruteForce(nums, target);
      // return combinationSum4ByMemoization(nums, target);
      return combinationSum4ByDP(nums, target);
    }

    // brute force, time complexity: O(n^target), space complexity: O(target)
    private int combinationSum4ByBruteForce(int[] nums, int target) {
      if (target < 0)
        return 0;
      if (target == 0)
        return 1;
      int count = 0;
      for (int n : nums) {
        count += combinationSum4ByBruteForce(nums, target - n);
      }
      return count;
    }

    // memoization, time complexity: O(n * target), space complexity: O(target)
    private int combinationSum4ByMemoization(int[] nums, int target) {
      Map<Integer, Integer> cache = new HashMap<>();
      cache.put(0, 1);
      return memoization(nums, target, cache);
    }

    private int memoization(int[] nums, int target, Map<Integer, Integer> cache) {
      if (cache.containsKey(target))
        return cache.get(target);
      int count = 0;
      for (int n : nums) {
        if (target - n >= 0) {
          count += memoization(nums, target - n, cache);
        }
      }
      cache.put(target, count);
      return count;
    }

    // dp, time complexity: O(n * target), space complexity: O(target)
    private int combinationSum4ByDP(int[] nums, int target) {
      int[] dp = new int[target + 1];
      dp[0] = 1;
      for (int i = 1; i <= target; i++) {
        for (int n : nums) {
          if (i - n >= 0) {
            dp[i] += dp[i - n];
          }
        }
      }
      return dp[target];
    }
  }
}
