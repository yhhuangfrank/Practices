package com.frank.mytest.test.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 1, 0, -3, 3};
        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums1)));
        System.out.println(Arrays.toString(productExceptSelf(nums2)));
    }

    /**
     * 想法：
     * 設一 n 個元素陣列
     * 計算 arr[0] = 1  與 arr[1] x arr[2]...x arr[n]
     * 計算 arr[1] = arr[0] 與  arr[2]...x arr[n]
     * 計算 arr[2] = arr[0]x arr[1] 與  arr[3]...x arr[n]
     * 計算 arr[n] = arr[0]x arr[1] x arr[3]...x arr[n-1] 與 1
     * 推導 => 計算 arr[i] = arr[0] x arr[1] ... x arr[i-1]  與  arr[i+1]...x arr[n]
     * <p>
     * 假設左邊部分乘積為 l, 右邊部分為 r
     * <p>
     * 遍歷到 i 位置 => l(i-1) 紀錄每個左邊的乘積
     * 右邊部分同理，反向從 length - 1 位置開始紀錄
     * 將左右陣列依照頭尾對應依序遍歷將位置乘上即答案
     */
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProducts = new int[len];
        int[] rightProducts = new int[len];
        int[] res = new int[len];
        int l = 1; // 代表左部分乘積
        int r = 1; // 代表右部分乘積

        for (int i = 0; i < len; i++) {
            if (i > 0) {
                l *= nums[i - 1];
            }
            leftProducts[i] = l;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (i != len - 1) {
                r *= nums[i + 1];
            }
            rightProducts[i] = r;
        }

        for (int i = 0; i < len; i++) {
            res[i] = leftProducts[i] * rightProducts[i];
        }

        return res;
    }

    public static int[] productExceptSelfV2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int r = 1; // 代表右部分乘積
        res[0] = 1;

        // 先算左部分乘積
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // 乘上右部分乘積
        for (int i = len - 1; i >= 0; i--) {
            res[i] = r * res[i];
            r *= nums[i];
        }

        return res;
    }
}
