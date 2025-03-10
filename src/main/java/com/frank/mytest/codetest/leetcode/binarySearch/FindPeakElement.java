package com.frank.mytest.codetest.leetcode.binarySearch;

public class FindPeakElement {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findPeakElement(new int[]{1, 2, 3, 1})); // 3 is a peak element, return 2
        System.out.println(sol.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4})); // return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
    }

    static class Solution {
        public int findPeakElement(int[] nums) {
            return helper(0, nums.length - 1, nums);
        }

        private int helper(int s, int e, int[] arr) {
            if (s > e) return -1;
            int m = s + (e - s) / 2;
            long cur = arr[m];
            long l = m - 1 >= 0 ? arr[m - 1] : Long.MIN_VALUE;
            long r = m + 1 < arr.length ? arr[m + 1] : Long.MIN_VALUE;
            if (cur > l && cur > r) return m;
            int i1 = helper(s, m - 1, arr);
            if (i1 != -1) return i1;
            return helper(m + 1, e, arr);
        }
    }
}
