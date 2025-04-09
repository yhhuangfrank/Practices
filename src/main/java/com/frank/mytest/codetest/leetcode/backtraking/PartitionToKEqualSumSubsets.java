package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
//        System.out.println(solution.canPartitionKSubsets(new int[]{1, 2, 3, 4}, 3));
        System.out.println(solution.canPartitionKSubsetsV2(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(solution.canPartitionKSubsetsV2(new int[]{1, 2, 3, 4}, 3));
    }

    static class Solution {
        // O(k^N) time
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }
            if (sum % k != 0) return false;
            int target = sum / k;
            int[] sumSets = new int[k];
            Arrays.sort(nums);
            return dfs(nums.length - 1, sumSets, target, nums);
        }

        private boolean dfs(int i, int[] sumSets, int target, int[] nums) {
            if (i == -1) return true;
            for (int j = 0; j < sumSets.length; j++) {
                if (nums[i] + sumSets[j] > target) continue;
                sumSets[j] += nums[i];
                if (dfs(i - 1, sumSets, target, nums)) return true;
                sumSets[j] -= nums[i];
            }
            return false;
        }

        // O(k*2^N) time
        public boolean canPartitionKSubsetsV2(int[] nums, int k) {
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) return false;
            boolean[] used = new boolean[nums.length]; // 數字是否使用過
            return backtrack(0, k, sum / k, 0, nums, used);
        }

        private boolean backtrack(int i, int numOfSet, int target, int subsetSum, int[] nums, boolean[] used) {
            if (numOfSet == 0) return true;
            if (subsetSum == target) return backtrack(0, numOfSet - 1, target, 0, nums, used); // 新一輪的 subset
            for (int j = i; j < nums.length; j++) { // 遍歷每個數字組成 subset
                if (used[j] || nums[j] + subsetSum > target) continue;
                used[j] = true;
                if (backtrack(j + 1, numOfSet, target, subsetSum + nums[j], nums, used)) return true;
                used[j] = false;
            }
            return false;
        }
    }
}
