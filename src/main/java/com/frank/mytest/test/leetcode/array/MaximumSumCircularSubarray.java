package com.frank.mytest.test.leetcode.array;

/**
 * 在一個環形陣列中，找到 subarray (連續元素) 的最大值
 * 環形陣列代表頭尾相連，假設長度為 n，某個 index i 位置的
 * 1) 下一個 index = (i + 1) % n
 * 2) 前一個 index = (i - 1 + n) % n
 */
public class MaximumSumCircularSubarray {
    public static void main(String[] args) {
        int[] arr = new int[] {9, -4, -7, 9};
        int[] arr1 = new int[] {5, -3, 5};
        System.out.println(maxSubarraySumCircularV2(arr));
        System.out.println(maxSubarraySumCircularV2(arr1));

    }

    public static int maxSubarraySumCircularV1(int[] nums) {
        // brute force
        int len = nums.length;
        int max = nums[0];

        for (int i = 0; i < len; i++) {
            int next = (i + 1) % len;
            int curr = nums[i];
            max = Math.max(curr, max);
            while (next != i) {
                curr += nums[next];
                max = Math.max(curr, max);
                next = (next + 1) % len;
            }
        }
        return max;
    }

    /**
     * 同時計算 maxSubarray、minSubarray 和 與 遍歷整個 array 的總和
     * 因為是環狀 array，計算 minSubarray 後，拿 total 減去中間可能的最小 subarray，即可計算 maxSubarray 發生在首尾相連的情況
     * 1) 若 max < 0，表示 arr 中都是負數，返回 max
     * 2) max > 0，比較 max(maxSubarray和, (整個 array 的總和 - minSubarray 和))
     */
    public static int maxSubarraySumCircularV2(int[] nums) {
        int max = nums[0];
        int currMax = 0;
        int min = nums[0];
        int currMin = 0;
        int total = 0;
        for (int n : nums) {
            currMax = Math.max(currMax, 0);
            currMax += n;
            max = Math.max(max, currMax);
            currMin = Math.min(currMin + n, n);
            min = Math.min(min, currMin);
            total += n;
        }
        System.out.printf("max {%s}, min {%s}, total {%s}%n", max, min, total);
        if (max < 0) return max;
        return Math.max(total - min, max);
    }


}
