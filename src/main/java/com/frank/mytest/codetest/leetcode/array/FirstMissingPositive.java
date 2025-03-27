package com.frank.mytest.codetest.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = new int[]{1, 2, 0};
        int[] nums2 = new int[]{3, 4, -1, 1};
        int[] nums3 = new int[]{7, 8, 9, 11, 12};
        System.out.println(sol.firstMissingPositive(nums1)); // 3
        System.out.println(sol.firstMissingPositive(nums2)); // 2
        System.out.println(sol.firstMissingPositive(nums3)); // 1
        System.out.println(sol.firstMissingPositiveV2(nums1));
        System.out.println(sol.firstMissingPositiveV2(nums2));
        System.out.println(sol.firstMissingPositiveV2(nums3));
    }

    static class Solution {
        // O(N) time, O(N) space
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            Set<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }
            // missing positive integer is between 1 - n + 1, inclusive
            for (int i = 1; i <= n; i++) {
                if (!numSet.contains(i)) return i;
            }
            return n + 1;
        }

        // O(N) time, O(1) space
        public int firstMissingPositiveV2(int[] nums) {
            // note: missing positive integer is between 1 - n + 1, inclusive
            int n = nums.length;
            // negative integers can be neglected
            for (int i = 0; i < n; i++) {
                if (nums[i] < 0) {
                    nums[i] = 0;
                }
            }
            // go through every positive integers nums[i] in nums, mark nums[abs(i) - 1] as negative to show nums[i] is already checked
            // edge cases:
            // 1. if nums[i] == 0, skip
            // 2. if nums[i] < 0, skip
            // 3. if nums[i - 1] == 0, make nums[i - 1] = -(nums.length + 1) to exclude this index
            for (int num : nums) {
                int val = Math.abs(num);
                if (1 <= val && val <= n) {
                    int i = val - 1;
                    if (nums[i] > 0) {
                        nums[i] *= -1;
                    } else if (nums[i] == 0) {
                        nums[i] = -(n + 1);
                    }
                }
            }
            // go through range [1, n + 1] to check if it's negative, if not, return it
            for (int i = 1; i <= n; i++) {
                if (nums[i - 1] >= 0) return i;
            }
            return n + 1; // edge case, like [1,2,3], return 4
        }
    }
}
