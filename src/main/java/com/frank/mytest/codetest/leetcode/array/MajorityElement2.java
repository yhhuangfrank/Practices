package com.frank.mytest.codetest.leetcode.array;

import java.util.*;

public class MajorityElement2 {

    public static void main(String[] args) {
        // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
        // ps : 最多只會有兩個數符合條件 (要超過3等分必然只有最多兩個)
        Solution sol = new Solution();
        int[] nums = new int[]{1, 1, 2, 2, 3};
        System.out.println(sol.majorityElement(nums));
        System.out.println(sol.majorityElementV2(nums));
    }

    static class Solution {
        public List<Integer> majorityElement(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int n : nums) {
                int cnt = count.getOrDefault(n, 0);
                count.put(n, cnt + 1);
            }
            List<Integer> res = new ArrayList<>();
            count.forEach((k, v) -> {
                if (v > nums.length / 3) {
                    res.add(k);
                }
            });
            return res;
        }

        // optimized, O(1) space, Boyer-Moore algo
        public List<Integer> majorityElementV2(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>(); // keeps size of 2
            for (int n : nums) {
                count.put(n, 1 + count.getOrDefault(n, 0));
                if (count.size() <= 2) continue;
                Map<Integer, Integer> temp = new HashMap<>();
                count.forEach((k, v) -> {
                    if (v > 1) {
                        temp.put(k, v - 1);
                    }
                });
                count = temp;
            }
            List<Integer> res = new ArrayList<>();
            // O(2N) time
            count.keySet().forEach(k -> {
                int trueCount = 0;
                for (int n : nums) {
                    if (n == k) trueCount++;
                }
                if (trueCount > nums.length / 3) {
                    res.add(k);
                }
            });
            return res;
        }
    }
}
