package com.frank.mytest.codetest.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到一整數 array 的 最左邊的 pivot index
 * pivot index (p) 條件為：
 * 在 p 的左邊所有元素總和 = 在 p 的右邊所有元素總和
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, 3, 6, 5, 6};
        int[] arr1 = new int[]{2, 1, -1};
        System.out.println(pivotIndex(arr)); // 3
        System.out.println(pivotIndex(arr1)); // 0
    }

    public static int pivotIndex(int[] nums) {
        PrefixSum prefixSum = new PrefixSum(nums);
        int sum1;
        int sum2;
        for (int i = 0; i < nums.length; i++) {
            sum1 = prefixSum.sumRange(0, i - 1);
            sum2 = prefixSum.sumRange(i + 1, nums.length - 1);
            if (sum2 == sum1) return i;
        }
        return -1;
    }
}

class PrefixSum {
    List<Integer> sum;

    public PrefixSum(int[] arr) {
        sum = new ArrayList<>();
        int total = 0;
        for (int n : arr) {
            total += n;
            sum.add(total);
        }
    }

    public int sumRange(int l, int r) {
        if (l >= sum.size() || r < 0) return 0;
        int curr = sum.get(r);
        int prev = l - 1 >= 0 ? sum.get(l - 1) : 0;
        return curr - prev;
    }
}
