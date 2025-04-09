package com.frank.mytest.codetest.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CarPooling {
//    Input: trips = [[2,1,5],[3,3,7]], capacity = 4
//    Output: false
//    Input: trips = [[2,1,5],[3,3,7]], capacity = 5
//    Output: true

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4)); // false
        System.out.println(solution.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5)); // true
        System.out.println(solution.carPooling(new int[][]{{1, 1, 7}, {1, 3, 7}, {2, 7, 8}}, 2)); // true
        System.out.println(solution.carPoolingV2(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4)); // false
        System.out.println(solution.carPoolingV2(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5)); // true
        System.out.println(solution.carPoolingV2(new int[][]{{1, 1, 7}, {1, 3, 7}, {2, 7, 8}}, 2)); // true
    }

    static class Solution {
        // O(NlogN) time
        public boolean carPooling(int[][] trips, int capacity) {
            Arrays.sort(trips, Comparator.comparingInt(a -> a[1]));
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // [num, to]
            int curNum = 0;
            for (int[] t : trips) { // [num of passengers, from , to]
                int num = t[0];
                int start = t[1];
                int end = t[2];
                while (!minHeap.isEmpty() && minHeap.peek()[1] <= start) { // 是否有下車
                    curNum -= minHeap.poll()[0];
                }
                curNum += num;
                if (curNum > capacity) return false;
                minHeap.add(new int[]{num, end});
            }
            return true;
        }

        // based on the limitation of from and to (0 <= from < to <= 1000)
        // O(n) time
        public boolean carPoolingV2(int[][] trips, int capacity) {
            int[] passChange = new int[1001];
            for (int[] t : trips) {
                int numPass = t[0];
                int start = t[1];
                int end = t[2];
                passChange[start] += numPass;
                passChange[end] -= numPass;
            }
            int curPass = 0;
            for (int i = 0; i < 1001; i++) {
                curPass += passChange[i];
                if (curPass > capacity) return false;
            }
            return true;
        }
    }
}
