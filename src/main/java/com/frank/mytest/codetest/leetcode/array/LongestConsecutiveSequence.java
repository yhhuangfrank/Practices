package com.frank.mytest.codetest.leetcode.array;

import java.util.*;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 4, 8, 5, 6, 7, 9, 3, 55, 88, 77, 99, 999999999};
        System.out.println(longestConsecutive(nums));
        Set<Integer> set1 = new HashSet<>(List.of(1,2,3));
        Set<Integer> set2 = new HashSet<>(List.of(3,2,1));
        System.out.println(set1);
        System.out.println(set1.containsAll(set2));
        System.out.println(set1);
    }

    // O(n) time
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int maxLen = 1;
        for (int n : set) { // 從 set 遍歷，去除重複的判斷
            int len = 1;
            int curr = n;
            if (!set.contains(curr - 1)) { // 0, 55, 77, 88, 99, 999999999
                while (set.contains(curr + 1)) {
                    len++;
                    curr++;
                }
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    // 暴力解
    public static int bruteForce(int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0];
        int max = nums[0];
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
            set.add(n);
        }
        int curr = min;
        int len = 1;
        int maxLen = 1;
        while (curr < max) {
            if (set.contains(curr + 1)) {
                len++;
                maxLen = Math.max(maxLen, len);
            } else {
                len = 0;
            }
            curr++;
        }
        return maxLen;
    }
}
