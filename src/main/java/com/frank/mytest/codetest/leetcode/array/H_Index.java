package com.frank.mytest.codetest.leetcode.array;

public class H_Index {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hIndex(new int[]{3, 0, 6, 1, 5})); // 3
        System.out.println(solution.hIndex(new int[]{1, 3, 1})); // 1
        System.out.println(solution.hIndex(new int[]{0})); // 0
        System.out.println(solution.hIndex(new int[]{100})); // 1
    }

    static class Solution {
        public int hIndex(int[] citations) {
            int l = 0;
            int r = citations.length + 1;
            while (l + 1 < r) {
                int m = l + (r - l) / 2;
                if (isValid(m, citations)) {
                    l = m;
                } else {
                    r = m;
                }
            }
            return l;
        }

        private boolean isValid(int h, int[] citations) {
            int count = 0;
            for (int c : citations) {
                if (c >= h) {
                    count++;
                }
                if (count >= h) return true;
            }
            return false;
        }
    }
}
