package com.frank.mytest.codetest.leetcode.interval;

import java.util.*;

/**
 * Input: nums = [1,2,3,4]
 * Output: 8
 * Explanation: The 8 possible good partitions are: ([1], [2], [3], [4]),
 * ([1], [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]), ([1,2], [3], [4]), ([1,2], [3,4]), ([1,2,3], [4]), and ([1,2,3,4]).
 * <p>
 * Input: nums = [1,1,1,1]
 * Output: 1
 * Explanation: The only possible good partition is: ([1,1,1,1]).
 * <p>
 * Input: nums = [1,2,1,3]
 * Output: 2
 * Explanation: The 2 possible good partitions are: ([1,2,1], [3]) and ([1,2,1,3]).
 */
public class CountTheNumberOfGoodPartitions {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfGoodPartitions(new int[]{1, 2, 3, 4})); // 8
        System.out.println(solution.numberOfGoodPartitions(new int[]{1, 1, 1, 1})); // 1
        System.out.println(solution.numberOfGoodPartitions(new int[]{1, 2, 1, 3})); // 2
        System.out.println(solution.numberOfGoodPartitions(new int[]{2, 4, 2, 7, 4,})); // 1
    }

    static class Solution {
        public int numberOfGoodPartitions(int[] nums) {
            Map<Integer, int[]> map = new HashMap<>(); // n -> [start, end]
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                if (!map.containsKey(n)) {
                    map.put(n, new int[]{i, -1});
                } else {
                    map.get(n)[1] = i;
                }
            }
            // 找到有多少不重複的範圍
            List<int[]> segments = new ArrayList<>(); // [start, end]
            for (Map.Entry<Integer, int[]> e : map.entrySet()) {
                if (e.getValue()[1] == -1) {
                    e.getValue()[1] = e.getValue()[0];
                }
                segments.add(e.getValue());
            }
            Collections.sort(segments, (s1, s2) -> s1[0] - s2[0]);
            int count = segments.size();
            int[] last = segments.get(0); // 紀錄前一個範圍
            for (int i = 1; i < segments.size(); i++) {
                int[] cur = segments.get(i);
                if (cur[0] < last[1]) {
                    count--;
                    last[1] = Math.max(last[1], cur[1]); // 取兩個範圍終點較遠的
                } else {
                    last = cur;
                }
            }
            int m = 1000000007;
            // 鄰近線段可以彼此連接組成更長的線段
            // n 段, n - 1 個空隙，總共可以選擇 C(n - 1, 0) + C(n - 1, 1) + ... + C(n - 1, n - 1) = 2^ (n - 1)
            int res = 1;
            for (int i = 0; i < count - 1; i++) {
                res *= 2;
                res %= m;
            }
            return res % m;
        }
    }
}
