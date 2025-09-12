package com.frank.mytest.codetest.leetcode.array;

import java.util.Arrays;

public class MaximumSubArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(solution.maxSubArray(new int[]{1})); // 1
        System.out.println(solution.maxSubArray(new int[]{5, 4, -1, 7, 8})); // 23

        System.out.println("----------------------------------------");
        System.out.println(Arrays.toString(solution.findMaxSubArrayIndex(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}))); // [3, 6]
        System.out.println(Arrays.toString(solution.findMaxSubArrayIndex(new int[]{1}))); // [0, 0]
        System.out.println(Arrays.toString(solution.findMaxSubArrayIndex(new int[]{5, 4, -1, 7, 8}))); // [0, 4]

    }

    static class Solution {
        public int maxSubArray(int[] nums) {
//      return maxSubArrayByBruteForce(nums);
            System.out.printf("maxSubArrayByFindMinPrefixSum ans is: %s%n", maxSubArrayByFindMinPrefixSum(nums));
            return maxSubArrayByKadane(nums);
        }

        private int maxSubArrayByFindMinPrefixSum(int[] nums) {
            // subArray(i, j) = prefixSum(j) - prefixSum(i - 1)
            int minPrefixSum = 0;
            int prefixSum = 0;
            int ans = nums[0];

            for (int n : nums) {
                prefixSum += n;
                ans = Math.max(ans, prefixSum - minPrefixSum);
                minPrefixSum = Math.min(minPrefixSum, prefixSum);
            }

            return ans;
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
                current = Math.max(0, current) + n; // make sure current is not negative
                max = Math.max(max, current);
            }
            return max;
        }

        // follow-up: find maxSum-subArray indexes
        public int[] findMaxSubArrayIndex(int[] nums) {
            int maxSum = nums[0];
            int currentSum = 0;
            int maxL = 0;
            int maxR = 0;
            int l = 0;

            for (int r = 0; r < nums.length; r++) {
                if (currentSum < 0) {
                    currentSum = 0;
                    l = r;
                }
                currentSum += nums[r];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxL = l;
                    maxR = r;
                }
            }

            return new int[]{maxL, maxR};
        }
    }
}
