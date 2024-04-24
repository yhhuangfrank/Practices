package com.frank.mytest.codetest.leetcode.dp;

/**
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * 在一整數陣列中找到子陣列並且他的總和為最大，返回其總和。子陣列為連續元素排列之陣列
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArrayByDP(nums));
    }

    // 暴力解
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // 初始最大值, 長度為 nums.length
        int max = 0;
        for (int num : nums) {
            max += num;
        }

        // 長度為 length - 1 的 subarray 到 長度 1
        int maxLen = nums.length - 1;
        while (maxLen > 0) {
            int start = 0;
            int end = start + maxLen;
            while (end <= nums.length) {
                int tempMax = 0;
                for (int i = start; i < end; i++) {
                    tempMax += nums[i];
                }
                if (tempMax > max) {
                    max = tempMax;
                }
                start++;
                end = start + maxLen;
            }
            maxLen--;
        }

        return max;
    }

    /**
     * 使用 dp
     * 想法： 假設 sub-array 是以某個元素在 i 位置作為結尾
     * 前一個 i - 1 位置結尾的 sub-array 的總和若小於 nums[i]，說明前面所有元素不會是最大的 subArray
     * 可推得 maxSubArray(nums, i) = nums[i] + Max(maxSubArray(nums, i-1), 0)
     * 初始條件： maxSubArray(nums, 0) = nums[0]
     */
    public static int maxSubArrayByDP(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + Math.max(dp[i - 1], 0);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
