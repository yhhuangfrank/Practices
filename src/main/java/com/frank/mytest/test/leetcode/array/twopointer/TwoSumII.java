package com.frank.mytest.test.leetcode.array.twopointer;

import java.util.Arrays;

/**
 * 給定一"排序好"的整數 array 與整數 target ，返回 index1 與 index2 使得
 * 1 <= index1 < index2 < nums.length 並且 nums[index1] + nums[index2] == target
 * 即代表回傳時，index1 比 index2 小，並且 index 是由 1 開始，也就是第幾個數
 */
public class TwoSumII {
    public static void main(String[] args) {
        int[] arr = new int[] {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(arr, 9)));
    }

    public static int[] twoSum(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                return new int[] {l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[0];
    }
}
