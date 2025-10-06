package com.frank.mytest.codetest.leetcode.dp;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // 4
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7, 7})); // 4
    }

    static class Solution {
        public int lengthOfLIS(int[] nums) {
            int maxLen = 0;
            int[] dp = new int[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                dp[i] = 1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] <= nums[i]) continue;
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }

            return maxLen;
        }
    }
}
