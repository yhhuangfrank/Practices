package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumNumberOfKDivisibleComponents {

//    Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
//    Output: 2
//    Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
//    Output: 3

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
//        System.out.println(solution.maxKDivisibleComponents(7, edges, new int[]{3, 0, 6, 1, 5, 2, 1}, 3)); // 3
        System.out.println(solution.maxKDivisibleComponents(7, edges, new int[]{1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000}, 7)); // 1
    }

    static class Solution {
        Map<Integer, List<Integer>> adj;
        int totalComponents = 0;

        public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
            this.adj = new HashMap<>();
            for (int i = 0; i < n; i++) {
                adj.put(i, new ArrayList<>());
            }
            for (int[] e : edges) {
                int n1 = e[0];
                int n2 = e[1];
                this.adj.get(n1).add(n2);
                this.adj.get(n2).add(n1);
            }

            // 如果 finalRootSum % k == 0，則它已經在 DFS 內部被計數。
            // 但由於題目要求的是連通組件的**最大數量**，我們只需要返回我們成功切斷的組件數量。
            // 實際上，即使 finalRootSum % k != 0, 整個樹仍然是一個組件。
            // 但在本題中，只需要計算成功切分的組件，因此返回 totalComponents 即可。
            long finalRootSum = dfs(0, -1, values, k);
            return this.totalComponents;
        }

        private long dfs(int cur, int parent, int[] values, int k) {
            long currentSubtreeSum = values[cur];
            for (int child : this.adj.get(cur)) {
                if (child != parent) {
                    currentSubtreeSum += dfs(child, cur, values, k);
                }
            }
            if (currentSubtreeSum % k == 0) {
                this.totalComponents++;
                // 這個組件已經獨立，它對父節點的貢獻是 0。
                // 這樣可以防止父節點 P 將這個組件 C 的值計入 P 的子樹和中。
                return 0;
            }
            return currentSubtreeSum;
        }
    }
}
