package com.frank.mytest.codetest.leetcode.array;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = new int[]{1, 2};
//        sol.rotate(nums1, 3);
//        sol.rotate(nums2, 3);
        sol.rotateV2(nums1, 3);
        sol.rotateV2(nums2, 3);
        System.out.println(Arrays.toString(nums1)); // [5,6,7,1,2,3,4]
        System.out.println(Arrays.toString(nums2)); // [2,1]
    }

    static class Solution {
        // O(N) time, O(N) space
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int size = k % n;
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                int idx = (i + size) % n;
                temp[idx] = nums[i];
            }
            for (int i = 0; i < n; i++) {
                nums[i] = temp[i];
            }
        }
        // O(N) time, O(1) space
        public void rotateV2(int[] nums, int k) {
            int n = nums.length;
            int size = k % n;
            // reverse entire array, then reverse sub arrays with size of k and n - k
            reverse(0, n - 1, nums);
            reverse(0, size - 1, nums);
            reverse(size, n - 1, nums);
        }

        private void reverse(int s, int e, int[] nums) {
            int l = s;
            int r = e;
            while (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l += 1;
                r -= 1;
            }
        }
    }
}
