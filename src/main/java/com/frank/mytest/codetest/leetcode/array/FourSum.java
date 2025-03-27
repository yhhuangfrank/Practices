package com.frank.mytest.codetest.leetcode.array;

import java.util.*;

public class FourSum {
//    Example 1:
//    Input: nums = [1,0,-1,0,-2,2], target = 0
//    Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//    Example 2:
//    Input: nums = [2,2,2,2,2], target = 8
//    Output: [[2,2,2,2]]

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = new int[]{1, 0, -1, 0, -2, 2};
        int[] nums2 = new int[]{2, 2, 2, 2, 2};
        int[] nums3 = new int[]{1, 0, -1, -1, -2, 0, -2, 2};
        int[] nums4 = new int[]{-3, -1, 0, 2, 4, 5};
        System.out.println(sol.fourSum(nums1, 0));
        System.out.println(sol.fourSum(nums2, 8));
        System.out.println(sol.fourSum(nums3, 0));
        System.out.println(sol.fourSum(nums4, 0));
        System.out.println(sol.fourSum(nums4, 2));
    }

    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            kSum(0, 4, nums, target, new ArrayDeque<>(), res);
            return res;
        }

        private void kSum(int s, int k, int[] nums, int target, Deque<Integer> arr, List<List<Integer>> res) {
            if (k != 2) {
                for (int i = s; i <= nums.length - k; i++) {
                    if (i > s && nums[i] == nums[i - 1]) continue;
                    arr.addLast(nums[i]);
                    kSum(i + 1, k - 1, nums, target - nums[i], arr, res);
                    arr.pollLast();
                }
                return;
            }
            // two sum
            int l = s;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < target) {
                    l += 1;
                } else if (nums[l] + nums[r] > target) {
                    r -= 1;
                } else {
                    arr.addLast(nums[l]);
                    arr.addLast(nums[r]);
                    res.add(new ArrayList<>(arr));
                    arr.pollLast();
                    arr.pollLast();
                    l += 1;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l += 1;
                    }
                }
            }
        }
    }
}
