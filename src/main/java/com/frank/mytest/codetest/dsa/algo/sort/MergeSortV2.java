package com.frank.mytest.codetest.dsa.algo.sort;

import java.util.Arrays;

public class MergeSortV2 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 6};
        mergeSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int s, int e) {
        if (e - s + 1 == 1) return;

        int m = s + (e - s) / 2;
        mergeSort(arr, s, m);
        mergeSort(arr, m + 1, e);

        merge(arr, s, m, e);
    }

    public static void merge(int[] arr, int s, int m, int e) {
        int[] left = Arrays.copyOfRange(arr, s, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, e + 1);
        int l = 0;
        int r = 0;
        int i = s;

        while (left.length > 0 && right.length > 0 && l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                arr[i] = left[l];
                l++;
            } else {
                arr[i] = right[r];
                r++;
            }
            i++;
        }

        while (left.length > 0 && l < left.length) {
            arr[i] = left[l];
            l++;
            i++;
        }

        while (right.length > 0 && r < right.length) {
            arr[i] = right[r];
            r++;
            i++;
        }
    }
}
