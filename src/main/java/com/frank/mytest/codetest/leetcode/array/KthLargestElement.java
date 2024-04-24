package com.frank.mytest.codetest.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 * <p>
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * <p>
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * ex: [3, 3, 2, 1, 1]
 * k = 3
 * n = 5-3 = 2 => [1,1,2,3,3]
 * 1) pivot = 1, l = [1], r = [2,3,3] => n > l.length
 * 2) [2,3,3], n = 2-l.length-1=2-1-1=0, pivot =0, l = [], r=[3,3] => n=l.length
 */
public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 2, 1, 1};
        System.out.println(findKthLargestV2(nums, 3));
//        System.out.println(Arrays.toString(nums));
    }

    // 穩定找出
    public static int findKthLargest(int[] nums, int k) {
        if (k > nums.length || k == 0) return -1;
        initMaxHeap(nums);

        int kthLargest = nums[0];
        int count = 0; // 執行了多少步
        int index = nums.length - 1;
        while (count < k) {
            kthLargest = nums[0];
            int temp = nums[0];
            nums[0] = nums[index];
            nums[index] = temp;
            index--; // heapSize 遞減
            maxHeapify(0, nums, index);
            count++;
        }

        return kthLargest;
    }

    private static void initMaxHeap(int[] nums) {
        for (int i = nums.length >> 1; i >= 0; i--) {
            maxHeapify(i, nums, nums.length - 1);
        }
    }

    private static void maxHeapify(int idx, int[] nums, int heapSize) {
        int l = 2 * idx + 1;
        int r = 2 * idx + 2;

        int largestIndex = idx;
        if (l <= heapSize && nums[l] > nums[largestIndex]) {
            largestIndex = l;
        }
        if (r <= heapSize && nums[r] > nums[largestIndex]) {
            largestIndex = r;
        }

        if (largestIndex != idx) {
            int temp = nums[largestIndex];
            nums[largestIndex] = nums[idx];
            nums[idx] = temp;
            maxHeapify(largestIndex, nums, heapSize);
        }
    }

    /**
     * 使用 quick sort 中的 partition
     * 想法：
     * 有一 n 個元素的陣列
     * 要找 "第 k 大" 的數，相當於要找 "第 n-k+1 小" 的數，此數的在排序好的陣列中會位在 "n - k" 的位置
     * 1. 進行 quick select ，找到 pivot 最終位置時的 index
     * 2.1 若 index = n-k => 回傳上面的數
     * 2.2 若 index > n-k => 往左半部遞迴，反之則往右半部遞迴
     */
    public static int findKthLargestV2(int[] nums, int k) { // 當重複數很多時，會錯誤
        // 改定義為 n = nums.length - k，意思為 第 n 小 的值
        int n = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, n);
    }

    public static int quickSelect(int[] nums, int l, int r, int n) {
        System.out.print("l: " + l);
        System.out.println(", r: " + r);
        System.out.println(Arrays.toString(nums) + " n: " + n);
        System.out.println("=============");
        if (l == r) return nums[l];
        int pivotIndex = partition(nums, l, r);

        if (pivotIndex == n) {
            return nums[pivotIndex];
        } else if (pivotIndex < n) {
            return quickSelect(nums, pivotIndex + 1, r, n);
        }
        return quickSelect(nums, l, pivotIndex - 1, n);
    }

    public static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
