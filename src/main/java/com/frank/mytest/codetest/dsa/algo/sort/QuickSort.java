package com.frank.mytest.codetest.dsa.algo.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[80000];
//        Random random = new Random();
//        for (int i = 0; i < arr.length; i++) {
//            int num = random.nextInt(1, 1000000);
//            arr[i] = num;
//        }
//        long start = System.currentTimeMillis();
//        quickSort(arr, 0, arr.length - 1);
//        long end = System.currentTimeMillis();
//        System.out.println("耗時： " + (end - start) + " ms");

        int[] arr = {-9, 0, 78, 0, -23, -567, 70, -1, 900, 4561};
//        quickSort(arr, 0, arr.length - 1);
        quickSortV2(arr, 0, arr.length - 1);
        System.out.println("排序後： " + Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int leftBound, int rightBound) {
        int l = leftBound; // 左指針
        int r = rightBound; // 右指針

        int pivot = arr[(leftBound + rightBound) / 2]; // 找中心點當作樞紐 (可以任意取 )
        int temp; // 用於交換

        while (l < r) {// 確保沒有交錯
            // 在 pivot 左邊一直找，直到找到比 pivot 大或相等的值
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在 pivot 右邊一直找，直到找到比 pivot 小或相等的值
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) { // 代表 pivot 左側的值都比 pivot 小
                break;
            }
            // 交換 l 與 r index 上的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 若交換完後，發現 arr[l] == pivot ，則右指針往前移動，否則會不斷交換相同值
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 若交換完後，發現 arr[r] == pivot ，則左指針往右移動
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 當 l == r 時，將指針移動到 pivot 兩側 (重要！)
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 對左邊的分組進行遞迴
        if (leftBound < r) {
            quickSort(arr, leftBound, r);
        }
        // 對右邊的分組進行遞迴
        if (rightBound > l) {
            quickSort(arr, l, rightBound);
        }
    }

    public static void quickSortV2(int[] arr, int left, int right) {
        if (left < right) { // 至少需有兩個元素
            int sortedIndex = partition(arr, left, right);
            quickSortV2(arr, left, sortedIndex - 1);
            quickSortV2(arr, sortedIndex + 1, right);
        }
    }

    // 以 pivot 為參考點分成兩組，最後回傳 pivot 所在位置
    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r]; // 以最後一個元素作為 pivot
        int i = l - 1; // 最後一個小於等於 pivot 的元素所在的 index
        for (int j = l; j < r; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r); // 移動 pivot 到 i+1 位置 (pivot 最後需在 "小於等於 pivot" 與 "大於 pivot 元素"之間)
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
