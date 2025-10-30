package com.frank.mytest.codetest.leetcode.greedy;

import java.util.*;

public class MaximizeGreatnessOfAnArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximizeGreatness(new int[]{1, 3, 5, 2, 1, 3, 1}));
        System.out.println(solution.maximizeGreatness(new int[]{1, 2, 3, 4}));
    }

    static class Solution {
        public int maximizeGreatness(int[] nums) {
//            return sol1(nums);
//            return sol2(nums);
            return sol3(nums);
        }

        /**
         * sorting + two pointer
         */
        private int sol3(int[] nums) {
            Arrays.sort(nums);
            int j = 0;
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                while (j < nums.length && nums[j] <= nums[i]) {
                    j++;
                }
                if (j != nums.length) {
                    res++;
                    j++;
                }
            }
            return res;
        }

        /**
         * binary search + sorting
         */
        public int sol2(int[] nums) {
            Map<Integer, Integer> freq = new HashMap<>();
            List<Integer> arr = new ArrayList<>();
            for (int n : nums) {
                if (!freq.containsKey(n)) {
                    freq.put(n, 1);
                    arr.add(n);
                } else {
                    freq.put(n, freq.get(n) + 1);
                }
            }
            Collections.sort(arr);
            int count = 0;
            for (int n : nums) {
                int idx = findMinGreat(n, arr);
                // System.out.println(freq);
                if (idx != -1) {
                    while (idx < arr.size() && freq.get(arr.get(idx)) == 0) {
                        idx++;
                    }
                    if (idx != arr.size()) {
                        count++;
                        freq.put(arr.get(idx), freq.get(arr.get(idx)) - 1);
                    }
                }
            }
            return count;
        }

        private int findMinGreat(int n, List<Integer> arr) {
            int l = 0;
            int r = arr.size() - 1;
            if (n == arr.get(arr.size() - 1)) return -1;
            while (l + 1 < r) {
                int m = l + (r - l) / 2;
                if (arr.get(m) > n) {
                    r = m;
                } else {
                    l = m;
                }
            }
            return r;
        }

        /**
         * use treeMap
         */
        private int sol1(int[] nums) {
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            for (int n : nums) {
                tree.put(n, tree.getOrDefault(n, 0) + 1);
            }
            int res = 0;
            for (int n : nums) {
                Integer higherKey = tree.higherKey(n);
                if (higherKey != null) {
                    res++;
                    int val = tree.get(higherKey) - 1;
                    if (val == 0) {
                        tree.remove(higherKey);
                    } else {
                        tree.put(higherKey, val);
                    }
                }
            }
            return res;
        }
    }
}
