package com.frank.mytest.codetest.leetcode.greedy;

public class SplitArrayIntoMaximumNumberOfSubarrays {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubarrays(new int[]{1, 0, 2, 0, 1, 2})); // 3
        System.out.println(solution.maxSubarrays(new int[]{5, 7, 1, 3})); // 1
    }

    static class Solution {
        /**
         * score 定義：一個 subarray 的每個元素取 bitwise AND
         * greedy 想法：
         * subarray 越長越可能算出來的 score為 0
         * 要讓 score 盡可能小，又能拆出越多的 subarray
         * 就計算每次 score 為 0 時，就拆分出一個 subarray
         */
        public int maxSubarrays(int[] nums) {
            int score = nums[0];
            for (int i = 1; i < nums.length; i++) {
                score &= nums[i];
            }
            if (score != 0 || nums.length == 1) return 1;

            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                int j = i;
                int temp = nums[j];
                while (j + 1 < nums.length && temp != 0) {
                    temp &= nums[j + 1];
                    j++;
                }
                if (temp == 0) { // 可獨立出一個 subarray
                    count++;
                    i = j;
                }
            }

            return count;
        }
    }
}
