package com.frank.mytest.test.dsa.algo.sort;

import java.util.Random;

public class HeapSort {
    private static int heapSize; // 需要構建成 MaxHeap 的節點個數

    public static void heapSort(int[] arr) {
        if (arr.length == 0 || arr.length == 1) return;
        final int ROOT_INDEX = 0;
        initMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            // 將 maxHeap 的 root 與 i 數值交換 (即 MaxHeap 的最後層由左至右數，的最後一個節點)
            swap(arr, i, ROOT_INDEX);
            heapSize -= 1;
            buildMaxHeap(ROOT_INDEX, arr); // 針對換上來的數值再次重新構建整個 maxheap
        }
    }

    // 將最初的未排序 array 做成 maxHeap
    private static void initMaxHeap(int[] arr) {
        heapSize = arr.length - 1;
        for (int i = arr.length / 2; i >= 0; i--) {
            buildMaxHeap(i, arr);
        }
    }

    private static void buildMaxHeap(int index, int[] arr) {
        int l = 2 * index + 1; // 指向 index 位置節點的左子節點
        int r = 2 * index + 2; // 指向 index 位置節點的右子節點

        int largestIndex = index; // 假設三個節點中的最大值是位在 index 節點上
        if (l <= heapSize && arr[l] > arr[largestIndex]) { // l 不可超過 heapSize
            largestIndex = l;
        }
        if (r <= heapSize && arr[r] > arr[largestIndex]) {
            largestIndex = r;
        }

        // 若最後 largestIndex 不是父節點而是左右子節點，則需交換數值，並繼續對 largestIndex 上數值繼續往下遞迴確認
        if (largestIndex != index) {
            swap(arr, largestIndex, index);
            buildMaxHeap(largestIndex, arr);
        }
    }

    private static void swap(int[] arr, int leftIndex, int rightIndex) {
        int temp = arr[leftIndex];
        arr[leftIndex] = arr[rightIndex];
        arr[rightIndex] = temp;
    }

    // 測試排序
    public static void main(String[] args) {
//        int[] arr = {1, 7, 99, 100, -2, -5, 25, 76, 84, 37};
//        HeapSort.heapSort(arr);
//        System.out.println("排序後： " + Arrays.toString(arr));

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(1, 1000000);
            arr[i] = num;
        }
        long start = System.currentTimeMillis();
        HeapSort.heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗時： " + (end - start) + " ms");
    }
}
