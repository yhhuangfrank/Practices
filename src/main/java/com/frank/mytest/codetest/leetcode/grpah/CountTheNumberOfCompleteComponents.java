package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

/**
 * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
 * Output: 3
 * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
 * Output: 1
 */
public class CountTheNumberOfCompleteComponents {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}})); // 3
        System.out.println(solution.countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}})); // 1
    }

    static class Solution {
        public int countCompleteComponents(int n, int[][] edges) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] e : edges) {
                int n1 = e[0];
                int n2 = e[1];
                adj.putIfAbsent(n1, new ArrayList<>());
                adj.putIfAbsent(n2, new ArrayList<>());
                adj.get(n1).add(n2);
                adj.get(n2).add(n1);
            }
            int count = 0;
            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (visited.contains(i)) continue;
                if (isComplete(i, adj, visited)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isComplete(int node, Map<Integer, List<Integer>> adj, Set<Integer> visited) {
            int[] pair = new int[2]; // nodes, edges
            dfs(node, adj, visited, pair);
            int nodes = pair[0];
            int completeEdges = nodes * (nodes - 1) / 2; // 每個節點兩兩相連時，總共的 edge 數
            return completeEdges == pair[1] / 2; // 同一條鞭會重複計算兩次，因此除以二
        }

        private void dfs(int cur, Map<Integer, List<Integer>> adj, Set<Integer> visited, int[] pair) {
            visited.add(cur);
            pair[0]++;
            for (int neighbor : adj.getOrDefault(cur, Collections.emptyList())) {
                pair[1]++;
                if (visited.contains(neighbor)) continue;
                dfs(neighbor, adj, visited, pair);
            }
        }
    }
}
