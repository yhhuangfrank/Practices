package com.frank.mytest.test.dsa.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = new int[80000];
//        Random random = new Random();
//        for (int i = 0; i < arr.length; i++) {
//            int num = random.nextInt(1, 1000000);
//            arr[i] = num;
//        }
//        long start = System.currentTimeMillis();
//        int[] sortedArr = mergeSort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("耗時： " + (end - start) + " ms");
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 10, -1, 99};
        int[] arr = {-5, -2, 3, 0, 1, 1};

//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        int[] sortedArr = mergeSort(arr);
        System.out.println(Arrays.toString(sortedArr));

    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr; // 若切到剩一個元素則返回

        int middle = (arr.length - 1) / 2;
        // 依照中點切分左右
        int[] leftArray = Arrays.copyOfRange(arr, 0, middle + 1);
        int[] rightArray = Arrays.copyOfRange(arr, middle + 1, arr.length);
        // 手動複製寫法
//        for (int i = 0; i < rightArray.length; i++) {
//            rightArray[i] = arr[i + middle + 1];
//        }

        // 向左遞迴 與 向右遞迴
        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftPointer = 0;
        int rightPointer = 0;
        int tempPointer = 0;

        // 1. 把左右兩邊有序的數據按照規則填充到 temp ，直到有一邊已經填充完畢
        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] <= right[rightPointer]) {
                result[tempPointer] = left[leftPointer];
                leftPointer++;
            } else {
                result[tempPointer] = right[rightPointer];
                rightPointer++;
            }
            tempPointer++;
        }
        // 2. 把有剩餘數據的一邊全部依次填入 temp
        while (leftPointer < left.length) {
            result[tempPointer] = left[leftPointer];
            leftPointer++;
            tempPointer++;
        }
        while (rightPointer < right.length) {
            result[tempPointer] = right[rightPointer];
            rightPointer++;
            tempPointer++;
        }

        return result;
    }
}
