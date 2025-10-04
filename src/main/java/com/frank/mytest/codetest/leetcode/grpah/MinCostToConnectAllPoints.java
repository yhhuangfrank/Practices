package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class MinCostToConnectAllPoints {

    // distance between (x1, y1) & (x2, y2) is |x1 - x2| + |y1 - y2|
//    Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//    Output: 20
//    Input: points = [[3,12],[-2,5],[-4,1]]
//    Output: 18
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}})); // 20
        System.out.println(solution.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}})); // 18
    }

    static class Solution {
        // Prim's algo
        public int minCostConnectPoints(int[][] points) {
            Map<Integer, List<int[]>> adj = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                int[] p1 = points[i];
                adj.putIfAbsent(i, new ArrayList<>());
                for (int j = i + 1; j < points.length; j++) {
                    int[] p2 = points[j];
                    adj.putIfAbsent(j, new ArrayList<>());
                    int dist = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                    adj.get(i).add(new int[] {dist, j});
                    adj.get(j).add(new int[] {dist, i});
                }
            }

            List<int[]> mst = new ArrayList<>(); // min spanning tree
            Set<Integer> visited = new HashSet<>();
            Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            for (int[] neighbor : adj.get(0)) {
                int dist = neighbor[0];
                int dst = neighbor[1];
                minHeap.add(new int[] {dist, 0, dst});
            }
            visited.add(0);

            while (!minHeap.isEmpty()) {
                int[] cur = minHeap.poll();
                int dist1 = cur[0];
                int src = cur[1];
                int dst1 = cur[2];
                if (visited.contains(dst1)) continue;
                visited.add(dst1);
                mst.add(new int[] {dist1, src, dst1});
                if (visited.size() == points.length) {
                    break;
                }
                for (int[] neighbor : adj.get(dst1)) {
                    int dist2 = neighbor[0];
                    int dst2 = neighbor[1];
                    if (visited.contains(dist2)) continue;
                    minHeap.add(new int[] {dist2, dst1, dst2});
                }
            }

            int res = 0;
            for (int[] pair : mst) {
                System.out.println(Arrays.toString(pair));
                res += pair[0];
            }
            return res;
        }
    }
}
