package com.frank.mytest.test.dsa.algo.sort;

import java.util.Arrays;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 0, 0, 3, 2, -50000, 50000};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 想法
    // 將數值對應 index ，並將數值個數放入
    public static void bucketSort(int[] arr) {
        int[] h = new int[100001]; // 假設數值範圍是 -50000 ~ 50000 ，共 100001 個
        for (int n : arr) {
            h[n + 50000] += 1; // 累計數量
        }
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            // 找到數量不為 0 的 index
            while (h[idx] == 0) {
                idx++;
            }
            arr[i] = idx - 50000; // 平移 50000 後轉回對應數值後放回 arr 中
            h[idx] -= 1;
        }
    }
}
