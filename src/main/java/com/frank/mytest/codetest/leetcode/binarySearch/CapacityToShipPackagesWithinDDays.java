package com.frank.mytest.codetest.leetcode.binarySearch;

public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)); // 15
        System.out.println(sol.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3)); // 6
        System.out.println(sol.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4)); // 3
    }

    static class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int l = weights[0];
            int r = 0;
            for (int w : weights) {
                l = Math.max(l, w);
                r += w;
            }
            int minCap = r;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (isValid(m, weights, days)) {
                    minCap = Math.min(minCap, m);
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return minCap;
        }

        private boolean isValid(int cap, int[] weights, int days) {
            int day = 1;
            int cur = 0;
            for (int w : weights) {
                if (cur + w > cap) {
                    day += 1;
                    if (day > days) return false;
                    cur = 0;
                }
                cur += w;
            }
            return true;
        }
    }
}
