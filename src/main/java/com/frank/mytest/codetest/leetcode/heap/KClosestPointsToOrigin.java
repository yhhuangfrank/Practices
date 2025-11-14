package com.frank.mytest.codetest.leetcode.heap;

import java.util.*;

/**
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
        System.out.println(Arrays.deepToString(solution.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
    }

    static class Solution {
        public int[][] kClosest(int[][] points, int k) {
            Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

            for (int[] p : points) {
                int dist = p[0] * p[0] + p[1] * p[1];
                int[] point = new int[] {dist, p[0], p[1]};
                if (maxHeap.size() < k) {
                    maxHeap.add(point);
                } else {
                    int[] top = maxHeap.peek();
                    if (dist < top[0]) {
                        maxHeap.poll();
                        maxHeap.add(point);
                    }
                }
            }

            int[][] res = new int[maxHeap.size()][2];
            int i = 0;
            while (!maxHeap.isEmpty()) {
                int[] poll = maxHeap.poll();
                res[i][0] = poll[1];
                res[i][1] = poll[2];
                i++;
            }
            return res;
        }
    }
}
