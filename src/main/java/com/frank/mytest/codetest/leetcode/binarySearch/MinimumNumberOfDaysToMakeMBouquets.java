package com.frank.mytest.codetest.leetcode.binarySearch;

public class MinimumNumberOfDaysToMakeMBouquets {
//    Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
//    Output: 3
//    Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
//    Output: -1
//    Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
//    Output: 12

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = new int[] {1,10,3,10,2};
        int[] arr2 = new int[] {7,7,7,7,12,7,7};
        System.out.println(solution.minDays(arr1, 3, 1)); // 3
        System.out.println(solution.minDays(arr1, 3, 2)); // -1
        System.out.println(solution.minDays(arr2, 2, 3)); // 12
    }

    static class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            if (bloomDay.length < m * k) return -1;

            int minD = bloomDay[0]; // 花最早開的日子
            int maxD = bloomDay[0]; // 花最晚開的日子
            for (int n : bloomDay) {
                minD = Math.min(minD, n);
                maxD = Math.max(maxD, n);
            }
            int l = minD;
            int r = maxD;
            int minDay = maxD + 1; // 不可能的答案
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (canMakeBouquets(mid, bloomDay, m, k)) {
                    minDay = Math.min(minDay, mid); // 若可以成功打包，紀錄最小值
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return minDay == maxD + 1 ? -1 : minDay;
        }

        // 可以打包 m 個花束
        private boolean canMakeBouquets(int d, int[] bloomDay, int m, int k) {
            int bouquets = 0;
            int count = 0;
            for (int n : bloomDay) {
                if (n <= d) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    bouquets++;
                    count = 0;
                }
                if (bouquets == m) {
                    return true;
                }
            }
            return false;
        }
    }
}
