package com.frank.mytest.test.dsa.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class Insertion {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(1, 1000000);
            arr[i] = num;
        }
        long start = System.currentTimeMillis();
        insertionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗時： " + (end - start) + " ms");
//        System.out.println("排序後 : " + Arrays.toString(arr));
    }

    // 最終版本
    private static void insertionSort(int[] arr) {
        // 從 index = 1 的數遍歷到最後一個數
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) { // 不同位置才需要往後移動，若相等則不用後移
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第 " + i + " 輪： " + Arrays.toString(arr));
        }
    }

    public static void insertionSortStepByStep(int[] arr) {
        // 逐步解析
        // 定義待插入的數
        int insertVal = arr[1]; // 第一輪判斷，幫 index = 1 的數找插入位置
        int insertIndex = 1 - 1; // 第一輪須從 index = 0 判斷

        // 1. insertIndex >= 0 保證給 insertVal 找到插入位置時，不越界
        // 2. insertVal < arr[insertIndex] 表示待插入的數還沒有找到插入位置
        // 3. 需要將 arr[insertIndex] 往後移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; // 後移
            insertIndex--;
        }
        // 當退出循環時，代表位置找到，此時需插入的位置為 insertIndex + 1
        // 舉例： 若 [5, 2] insertIndex = 0 時會進行 loop 操作，最終變為 -1，而 2 需要方在 index = 0 位置，即 -1 + 1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一輪插入");
        System.out.println(Arrays.toString(arr));

        // 第二輪
        insertVal = arr[2];
        insertIndex = 2 - 1; // 從 index = 1 開始判斷
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二輪插入");
        System.out.println(Arrays.toString(arr));
    }
}
