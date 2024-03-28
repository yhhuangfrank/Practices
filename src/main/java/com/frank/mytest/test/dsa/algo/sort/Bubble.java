package com.frank.mytest.test.dsa.algo.sort;

import java.util.Random;

public class Bubble {
    // O(n^2)
    public static void main(String[] args) {
//        int[] arr = new int[]{3, 9, -1, 10, -2};
//        bubbleSort(arr);
//        System.out.println("排序後: " + Arrays.toString(arr));

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(1, 1000000);
            arr[i] = num;
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗時： " + (end - start) + " ms");
    }

    private static void swap(int num1, int num2, int[] arr) {
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }

    private static void bubbleSort(int[] arr) {
        boolean isNoSwap = true;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isNoSwap = false;
                    swap(j, j + 1, arr);
                }
            }
            if (isNoSwap) {
                break;
            } else {
                isNoSwap = true; // 重置
            }
        }
    }
}
