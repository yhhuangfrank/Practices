package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

/*
Input: n = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Explanation:
Gardens 1 and 2 have different types.
Gardens 2 and 3 have different types.
Gardens 3 and 1 have different types.
Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].

Example 2:
Input: n = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]

Example 3:
Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 */
public class FlowerPlantingWithNoAdjacent {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
        System.out.println(Arrays.toString(solution.gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}})));
        System.out.println(Arrays.toString(solution.gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}})));
    }

    static class Solution {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        public int[] gardenNoAdj(int n, int[][] paths) {
            int[] flowers = new int[n + 1];
            for (int[] p : paths) {
                int n1 = p[0];
                int n2 = p[1];
                this.adj.putIfAbsent(n1, new ArrayList<>());
                this.adj.putIfAbsent(n2, new ArrayList<>());
                this.adj.get(n1).add(n2);
                this.adj.get(n2).add(n1);
            }

            for (int i = 1; i <= n; i++) {
                boolean[] used = new boolean[5]; // 哪些編號被用過
                for (int neighbor : this.adj.getOrDefault(i, new ArrayList<>())) {
                    int flowerId = flowers[neighbor];
                    if (flowerId != 0) {
                        used[flowerId] = true;
                    }
                }
                // 找一個沒有用過的編號
                for (int j = 1; j < used.length; j++) {
                    if (!used[j]) {
                        flowers[i] = j;
                        break;
                    }
                }
            }

            int[] res = new int[n];
            System.arraycopy(flowers, 1, res, 0, n);
            return res;
        }
    }
}
