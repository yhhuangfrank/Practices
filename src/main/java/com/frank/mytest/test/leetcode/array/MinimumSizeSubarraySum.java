package com.frank.mytest.test.leetcode.array;

/**
 * 在給定一正整數 array 與一正整數 target，找到最小長度的 subarray ，其和為 大於等於 target 值
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 2, 4, 3};
        int[] arr1 = new int[]{1, 4, 4};
        int[] arr2 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(minSubArrayLen(7, arr));
        System.out.println(minSubArrayLen(4, arr1));
        System.out.println(minSubArrayLen(11, arr2));
        System.out.println(minSubArrayLenByBruteForce(7, arr));
        System.out.println(minSubArrayLenByBruteForce(4, arr1));
        System.out.println(minSubArrayLenByBruteForce(11, arr2));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int L = 0;
        int sum = 0;
        for (int R = 0; R < nums.length; R++) {
            sum += nums[R];
            while (sum >= target) {
                minLength = Math.min(minLength, R - L + 1);
                sum -= nums[L];
                L++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int minSubArrayLenByBruteForce(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int sum;
        for (int L = 0; L < nums.length; L++) {
            sum = 0;
            for (int R = L; R < nums.length; R++) {
                sum += nums[R];
                if (sum >= target) {
                    minLength = Math.min(minLength, R - L + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
