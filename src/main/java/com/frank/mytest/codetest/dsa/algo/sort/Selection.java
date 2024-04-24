package com.frank.mytest.codetest.dsa.algo.sort;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {

        int[] arr = {101, 34, 119, 1};
        System.out.println("排序前： " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("排序後： " + Arrays.toString(arr));
    }

    /*
     * 假如原始數據 [101, 34, 119, 1]
     * 第一輪在 4 個數字中找最小值為 1，與 101 交換位置，變為 [1, 34, 119, 101]
     * 第二輪在 3 個數字 (34, 119, 101)找最小值為 34，與第二個元素 34 交換，整體沒變
     * 第三輪 119, 101 找最小值 101，與第三個元素 119 交換，變為 [1, 34, 101, 119]
     */
    public static void selectionSort(int[] arr) {
        // 要比較 arr.length - 1 次
        for (int i = 0; i < arr.length - 1; i++) {
            // 假定每次循環的第一個元素為最小值
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            // 若最小值與目前的 arr[i] 不同才交換
            if (min != arr[i]) {
                int temp = arr[i];
                arr[minIndex] = temp;
                arr[i] = min;
            }
            System.out.println("第 " + (i + 1) + " 次排序，目前為： " + Arrays.toString(arr));
        }
    }
}
