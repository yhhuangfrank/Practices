package com.frank.mytest.test.dsa.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Shell Sort 是對 Insertion Sort 的改進 (尤其是需要的移動次數很多時的情況)
 * 將 array 進行分組，第一次以 arr.length / 2 長度 (取 floor 整數) 分出 n 個組
 * 對每一組進行 insertion sort，做完後再對 n / 2
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}; // 幾乎是從大到小排
        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(1, 1000000);
            arr[i] = num;
        }
        long start = System.currentTimeMillis();
//        shellSortWithExchangeStepByStep(arr);
//        shellSortWithExchange(arr);
        shellSortWithShift(arr);
        long end = System.currentTimeMillis();
//        System.out.println("排序後 : " + Arrays.toString(arr));
        System.out.println("耗時： " + (end - start) + " ms");
    }

    // 使用交換
    private static void shellSortWithExchange(int[] arr) {
        // 從 index = 1 的數遍歷到最後一個數
        int gap = arr.length / 2;
        int temp;
        do {
            for (int i = gap; i < arr.length; i++) {
                // 一直往前找 gap 個元素比較大小，小的換到前面
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            gap /= 2;
        } while (gap != 0);
    }

    // 交換的優化 -> 移位法
    private static void shellSortWithShift(int[] arr) {
        // 定義 gap ，並逐步縮小
        int gap = arr.length / 2;

        do {
            // 從第 gap 個元素，逐個對其所在的組進行 insertion sort
            insertionSort(arr, gap);
            gap /= 2;
        } while (gap != 0);
    }

    private static void insertionSort(int[] arr, int gap) {
        int insertVal;
        int insertIndex;

        for (int i = gap; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - gap;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + gap] = arr[insertIndex];
                insertIndex -= gap;
            }
            if (insertIndex + gap != i) { // 不同位置才需要往後移動，若相等則不用後移
                arr[insertIndex + gap] = insertVal;
            }
        }
    }

    public static void shellSortWithExchangeStepByStep(int[] arr) {
        // 逐步解析，假定 arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        // 第一輪，arr.length / 2 = 10 / 2 = 5，分為五組 (每組 2 個)
        int temp;
        for (int i = 5; i < arr.length; i++) {
            // gap = 5，往前找五個元素比較大小，小的換到前面
            for (int j = i - 5; j >= 0 ; j-= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一輪後： " + Arrays.toString(arr));

        // 第二輪， 5 / 2 = 2，分為 2 組 (每組 5 個)
        for (int i = 2; i < arr.length; i++) {
            // gap = 2，往前找兩個元素比較大小，小的換到前面
            for (int j = i - 2; j >= 0 ; j-= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二輪後： " + Arrays.toString(arr));

        // 第三輪 2 / 2 = 1，分為 1 組，即整個 array
    }
}
