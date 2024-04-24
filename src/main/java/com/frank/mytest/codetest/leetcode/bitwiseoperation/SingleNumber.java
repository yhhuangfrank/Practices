package com.frank.mytest.codetest.leetcode.bitwiseoperation;

public class SingleNumber {
    public static void main(String[] args) {
        // 在一個 array 內，只有一個元素數量為一，其餘的元素皆為兩個
        // 寫一個方法找到數量為一的元素
        int[] arr = {1, 2, 1, 2, 4};
        System.out.println(singleNumber(arr));
    }

    public static int singleNumber(int[] nums) {
        // a XOR a = 0, a XOR 0 = a, 0 XOR a = a 。 XOR 滿足交換律和結合律
        // 對數列每個元素進行 XOR 運算 ->  1^2^1^2^4 => (1^1)^(2^2)^4(交換與結合) => 0^0^4 => 4 (找到剩下的元素)
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
