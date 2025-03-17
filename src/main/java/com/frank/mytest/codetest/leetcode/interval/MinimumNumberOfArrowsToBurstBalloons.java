package com.frank.mytest.codetest.leetcode.interval;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        // 每個 point 表示氣球直徑範圍
        // 箭沿著 y 軸方向飛行，找最少需要幾支箭射破所有氣球
        Solution sol = new Solution();
        int[][] points1 = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int[][] points2 = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        int[][] points3 = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
        System.out.println(sol.findMinArrowShots(points1));
        System.out.println(sol.findMinArrowShots(points2));
        System.out.println(sol.findMinArrowShots(points3));
//        System.out.println((long) (-2147483646) - (2147483646));
    }

    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // 找相連的 intervals
            int n = points.length;
            Arrays.sort(points, Comparator.comparingInt(p -> p[0]));
            int count = n;
            int[] p1 = points[0];
            for (int i = 1; i < n; i++) {
                int[] p2 = points[i];
                if (p2[0] <= p1[1]) {
                    count -= 1;
                    p1[1] = Math.min(p1[1], p2[1]); // 須限制在兩個 point 重疊處的上限
                } else {
                    p1 = p2;
                }
            }
            return count;
        }
    }
}
