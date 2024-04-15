package com.frank.mytest.test.dsa.algo.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 在一給定 array 中，判斷一個長度為 k 的subarray 是否有重複的元素
 */
public class SlidingWindow {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 3, 3};
        int windowSize = 3;
        System.out.println(isDuplicateInWindowV2(arr, windowSize));
    }

    // 暴力解法，固定 window 大小後，一一比對
    public static boolean isDuplicateInWindowV1(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < Math.min(arr.length, i + k); j++) {
                if (arr[i] == arr[j]) return true;
            }
        }
        return false;
    }

    // 使用 set 配合 window 判斷
    public static boolean isDuplicateInWindowV2(int[] arr, int k) {
        int l = 0;
        Set<Integer> set = new HashSet<>();
        for (int r = 0; r < arr.length; r++) {
            if (r - l + 1 > k) {
                set.remove(arr[l]);
                l++;
            }
            if (set.contains(arr[r])) return true;
            set.add(arr[r]);
        }
        return false;
    }
}
