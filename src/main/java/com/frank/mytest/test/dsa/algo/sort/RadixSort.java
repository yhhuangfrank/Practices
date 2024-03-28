package com.frank.mytest.test.dsa.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(1, 10000000);
            arr[i] = num;
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗時： " + (end - start) + " ms");

//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println("排序後： " + Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        // 定義一個二維 array ，表示 10 個桶，每個桶皆為一維陣列。確保一定放得下所有 arr 的數字，桶的大小定為 arr.length，以空間換時間
        int[][] buckets = new int[10][arr.length];

        // 為了記錄每個桶中，實際存放多少數據，定義一個一維陣列來記錄各個桶的每次放入的數據個數
        int[] bucketElementCounts = new int[10]; // ex: bucketElementCounts[0] 表示第一個桶的數據個數

        int steps = getRadixSortStep(arr); // 需要循環之次數
        int factor; // 計算位數使用的係數

        for (int i = 0; i < steps ; i++) {
            //*** 取出數據放入桶中 ***//
            for (int num : arr) {
                factor = (int) Math.pow(10, i);
                int digit = (num / factor) % 10; // 取出位數 (個位十位百位...)
                buckets[digit][bucketElementCounts[digit]] = num; // bucketElementCounts[digit] 此桶的個數代表在桶中的 index 位置
                bucketElementCounts[digit]++;
            }
            //*** 按照桶的順序與桶中 index，依序取出來放入原有 arr *** //
            int index = 0; // array 指針
            for (int j = 0; j < buckets.length ; j++) {
                if (bucketElementCounts[j] != 0) {
                    // 取出該桶中數據
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = buckets[j][k];
                    }
                }
                // 清空 bucketElementCounts 紀錄個數，讓下一輪使用 (重要)
                bucketElementCounts[j] = 0;
            }
        }
    }

    private static int getRadixSortStep(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 判斷 max 的位數
//        if (max / 10 == 0) return 1;
//        int count = 0;
//        while (max != 0) {
//            max /= 10;
//            count++;
//        }
//        return count;

        // 判斷 max 的位數方法 2
        return (max + "").length();
    }

    // 逐步解析
    private static void radixSortStepByStep(int[] arr) {

        // 定義一個二維 array ，表示 10 個桶，每個桶皆為一維陣列。確保一定放得下所有 arr 的數字，桶的大小定為 arr.length，以空間換時間
        int[][] buckets = new int[10][arr.length];

        // 為了記錄每個桶中，實際存放多少數據，定義一個一維陣列來記錄各個桶的每次放入的數據個數
        int[] bucketElementCounts = new int[10]; // ex: bucketElementCounts[0] 表示第一個桶的數據個數

        // 第一輪，針對個位數
        for (int num : arr) {
            int unitDigit = num % 10; // 取出個位數
            buckets[unitDigit][bucketElementCounts[unitDigit]] = num; // bucketElementCounts[unitDigit] 此桶的個數代表在桶中的 index 位置
            bucketElementCounts[unitDigit]++;
        }
        // 按照桶的順序與桶中 index，依序取出來放入原有 arr
        int index = 0; // array 指針
        for (int i = 0; i < buckets.length ; i++) {
            if (bucketElementCounts[i] != 0) {
                // 取出該桶中數據
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = buckets[i][j];
                }
            }
            // 第一輪後，清空 bucketElementCounts 紀錄個數，讓下一輪使用 (重要)
            bucketElementCounts[i] = 0;
        }
        System.out.println("第一輪： " + Arrays.toString(arr));

        // 第二輪，針對十位數
        for (int num : arr) {
            int tensDigit = (num / 10) % 10; // 取出十位數
            buckets[tensDigit][bucketElementCounts[tensDigit]] = num;
            bucketElementCounts[tensDigit]++;
        }
        // 按照桶的順序與桶中 index，依序取出來放入原有 arr
        index = 0; // array 指針
        for (int i = 0; i < buckets.length ; i++) {
            if (bucketElementCounts[i] != 0) {
                // 取出該桶中數據
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = buckets[i][j];
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第二輪： " + Arrays.toString(arr));

        // 第二輪，針對百位數
        for (int num : arr) {
            int tensDigit = (num / 100) % 10; // 取出百位數
            buckets[tensDigit][bucketElementCounts[tensDigit]] = num;
            bucketElementCounts[tensDigit]++;
        }
        // 按照桶的順序與桶中 index，依序取出來放入原有 arr
        index = 0; // array 指針
        for (int i = 0; i < buckets.length ; i++) {
            if (bucketElementCounts[i] != 0) {
                // 取出該桶中數據
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = buckets[i][j];
                }
            }
        }
        System.out.println("第三輪： " + Arrays.toString(arr));
    }
}
