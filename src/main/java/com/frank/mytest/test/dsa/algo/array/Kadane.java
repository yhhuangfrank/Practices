package com.frank.mytest.test.dsa.algo.array;

import java.util.Arrays;

/**
 * 適用於在一 array 中找尋連續的元素 (sub-array)，其總和為最大
 */
public class Kadane {
    public static void main(String[] args) {
        int[] arr = new int[] {4, -1, 2, -7, 3, 4};
        System.out.println(bruteForce(arr));
        System.out.println(findMaxSubArraySum(arr));
        System.out.println(Arrays.toString(findMaxSubArrayIndex(arr)));
    }

    // 暴力解法，找到每個 subArr 的組合
    private static int bruteForce(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int curr = 0;
            for (int j = i; j < arr.length; j++) {
                curr += arr[j];
                max = Math.max(max, curr);
            }
        }
        return max;
    }

    /**
     *  想法： 定義一 currSum 計算暫時的 subArray 總和，當目前的 currSum 已經小於 0 ，可以直接重置為 0
     *  因為若為負數，加上任意數字只會更小，相當於重新定義 subArray 的起點
     */
    private static int findMaxSubArraySum(int[] arr) {
        int max = arr[0];
        int curr = 0;

        for (int n : arr) {
//            curr = Math.max(curr, 0); // 確保為正數
//            curr += n;
            curr = Math.max(curr + n, n); // 可簡化為一行
            max = Math.max(max, curr);
        }
        return max;
    }

    /**
     * 若要改找尋 maxSubarray 的 index，需結合 sliding window 方法
     */
    private static int[] findMaxSubArrayIndex(int[] arr) {
        if (arr.length == 0) return new int[0];
        int max = arr[0];
        int curr = 0;
        int l = 0;
        int r = 0;  // 固定向前移動
        int maxL = 0;
        int maxR = 0;

        for (int n : arr) {
            if (curr < 0) {
                curr = 0;
                l = r;
            }
            curr += n;
            if (curr > max) {
                max = curr;
                maxL = l;
                maxR = r;
            }
            r++;
        }

        return new int[] {maxL, maxR};
    }

}
