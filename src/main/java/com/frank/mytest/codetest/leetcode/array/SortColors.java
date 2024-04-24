package com.frank.mytest.codetest.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * <p>
 * 0,1,2 分別表示紅色、白色和藍色，將一個 array 按照 012 順序在內部排列好
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Example 2
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
public class SortColors {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 2};
        int[] arr2 = new int[]{1, 2, 0};
        int[] arr3 = new int[]{0, 2};
//        sortColorsV2(arr);
        System.out.println("=========");
        sortColorsV2(arr2);
        System.out.println("=========");
//        sortColorsV2(arr3);
//        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
//        System.out.println(Arrays.toString(arr3));
    }

    public static void sortColors(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.merge(num, 1, Integer::sum);
        }

        int index = 0;
        int incr = 0;
        while (index < nums.length) {
            Integer count = countMap.get(incr);
            if (count == null && incr < 2) {
                incr++;
            } else if (count != null) {
                nums[index] = incr;
                index++;
                countMap.put(incr, count - 1);
                if (count - 1 == 0) {
                    incr++;
                }
            }
        }

    }

    /**
     * 想法：
     * 設定兩個 pointer 指向 "最後一個放 0 的位置 (i0)" 與 “第一個放 2 的位置 (i2)“
     * 遍歷整個數列，當遇到 i 的位置上數字為
     * 1) 0 : 將 nums[i] 與 nums[i0] 交換，i0 遞增
     * 2) 2 : 將 nums[i] 與 nums[i2] 交換，i2 遞減。
     * 由於 i2 位置上的原本可能也是 2，因此需將 i 遞減，直到交換過來的不是 2
     * 迴圈一直執行到 i 與 i2 交會，迴圈結束時，0,2 都排好了，1 自然會在中間
     */
    public static void sortColorsV2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;

        int i = 0; // 指向目前的位置
        int i0 = 0; // 代表最後一個放 0 的位置
        int i2 = nums.length - 1; // 代表第一個放 2 的位置
        int temp;

        while (i <= i2) {
            if (nums[i] == 2) {
                temp = nums[i];
                nums[i] = nums[i2];
                nums[i2] = temp;
                // 可能換來的還是2，因此 i 再判斷一遍
                i2--;
                i--;
            } else if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[i0];
                nums[i0] = temp;
                i0++;
            }
            i++;
        }
    }
}
