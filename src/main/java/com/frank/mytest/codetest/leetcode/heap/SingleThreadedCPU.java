package com.frank.mytest.codetest.leetcode.heap;

import java.util.*;

public class SingleThreadedCPU {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] tasks = new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        int[][] tasks2 = new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}};
        System.out.println(Arrays.toString(solution.getOrder(tasks))); // [0,2,3,1]
        System.out.println(Arrays.toString(solution.getOrder(tasks2))); // [4,3,2,0,1]
    }

    static class Solution {
        public int[] getOrder(int[][] tasks) {
            int n = tasks.length;
            // [enqueueTime, processTime, index]
            List<Integer[]> taskList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                taskList.add(new Integer[] { tasks[i][0], tasks[i][1], i });
            }
            taskList.sort((a, b) -> a[0] - b[0]);
            // [processTime, index]
            PriorityQueue<Integer[]> scheduler = new PriorityQueue<>((a, b) -> Objects.equals(a[0], b[0]) ? a[1] - b[1] : a[0] - b[0]);
            long time = taskList.get(0)[0];
            int i = 0;
            int[] res = new int[n];
            int resIdx = 0;
            while (i < n || !scheduler.isEmpty()) {
                while (i < n && taskList.get(i)[0] <= time) {
                    scheduler.add(new Integer[] { taskList.get(i)[1], taskList.get(i)[2] });
                    i++;
                }
                if (scheduler.isEmpty()) { // idle
                    time = taskList.get(i)[0];
                } else {
                    Integer[] poll = scheduler.poll();
                    int processTime = poll[0];
                    int idx = poll[1];
                    res[resIdx++] = idx;
                    time += processTime;
                }
            }
            return res;
        }
    }
}
