package com.frank.mytest.codetest.leetcode.binarySearch;

public class MinimumTimeToRepairCars {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.repairCars(new int[]{4, 2, 3, 1}, 10)); // 16
        System.out.println(solution.repairCars(new int[]{5, 1, 8}, 6)); // 16
    }

    static class Solution {
        public long repairCars(int[] ranks, int cars) {
            long minRank = ranks[0];
            for (int r : ranks) {
                minRank = Math.min(minRank, r);
            }
            long l = 0;
            long r = minRank * (long) cars * (long) cars;
            while (l + 1 < r) {
                long m = l + (r - l) / 2;
                if (canFixInTime(m, ranks, cars)) {
                    r = m;
                } else {
                    l = m;
                }
            }
            return r;
        }

        private boolean canFixInTime(long t, int[] ranks, long carCount) {
            long canFixedCount = 0;
            for (int r : ranks) {
                long maxCarCanFixed = (long) Math.sqrt((double) t / r);
                canFixedCount += maxCarCanFixed;
            }
            return canFixedCount >= carCount;
        }
    }
}
