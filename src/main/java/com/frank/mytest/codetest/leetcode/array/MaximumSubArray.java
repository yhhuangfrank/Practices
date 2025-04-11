package com.frank.mytest.codetest.leetcode.array;

public class MaximumSubArray {

  public static void main(String[] args) {
    Solution solution = new Solution(); 
    System.out.println(solution.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // 6
    System.out.println(solution.maxSubArray(new int[] {1})); // 1
    System.out.println(solution.maxSubArray(new int[] {5,4,-1,7,8})); // 23
  }

  static class Solution {
    public int maxSubArray(int[] nums) {
      return maxSubArrayByBruteForce(nums);
    }

    // brute force solution, O(n^2) time complexity
    public int maxSubArrayByBruteForce(int[] nums) {
      int max = nums[0];
      for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
          sum += nums[j];
          max = Math.max(max, sum);
        }
      }
      return max;
    }

    // kadane's algorithm, O(n) time complexity
    public int maxSubArrayByKadane(int[] nums) {
      int max = nums[0];
      int current = 0;
      for (int n : nums) {
        current = Math.max(n, current + n);
        max = Math.max(max, current);
      }
      return max;
    }
  }
}
