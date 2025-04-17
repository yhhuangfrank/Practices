package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class MinimumHeightTrees {
    // given a graph with n nodes and n - 1 edges
    // determine all roots with the minimum height for the tree constructed by nodes in graph
    // key : at most two roots share the same minimum height
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] edges1 = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        int[][] edges2 = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(solution.findMinHeightTrees(4, edges1)); // 1
        System.out.println(solution.findMinHeightTrees(6, edges2)); // [3, 4]
        System.out.println(solution.findMinHeightTrees(1, new int[0][0])); // [0]
    }

    static class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
//            return findMinHeightTreesByDFS(n, edges);
            return findMinHeightTreesByCutLeaves(n, edges);
        }

        // 每次針對 leaves (僅有一個neighbor) 減少其 edgeCount, 直到剩下至多兩個 nodes
        // 類似 BFS 的方式
        private List<Integer> findMinHeightTreesByCutLeaves(int n, int[][] edges) {
            // 建構 adjacent map
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] e : edges) {
                adj.putIfAbsent(e[0], new ArrayList<>());
                adj.get(e[0]).add(e[1]);
                adj.putIfAbsent(e[1], new ArrayList<>());
                adj.get(e[1]).add(e[0]);
            }
            Deque<Integer> leaves = new ArrayDeque<>();
            Map<Integer, Integer> edgeCount = new HashMap<>();
            adj.forEach((src, neighbors) -> {
                if (neighbors.size() == 1) {
                    leaves.addLast(src);
                }
                edgeCount.put(src, neighbors.size());
            });
            List<Integer> res = new ArrayList<>(List.of(0)); // n == 1
            int total = n;

            while (!leaves.isEmpty()) {
                if (total <= 2) return leaves.stream().toList();
                int size = leaves.size();

                for (int i = 0; i < size; i++) {
                    Integer node = leaves.pollFirst();
                    total -= 1;
                    for (int neighbor : adj.get(node)) {
                        edgeCount.put(neighbor, edgeCount.get(neighbor) - 1);
                        if (edgeCount.get(neighbor) == 1) {
                            leaves.addLast(neighbor); // 找到下一層的 leaf node
                        }
                    }
                }
            }
            return res;
        }

        private List<Integer> findMinHeightTreesByDFS(int n, int[][] edges) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] e : edges) {
                adj.putIfAbsent(e[0], new ArrayList<>());
                adj.putIfAbsent(e[1], new ArrayList<>());
                adj.get(e[0]).add(e[1]);
                adj.get(e[1]).add(e[0]);
            }
            List<Integer> heights = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                heights.add(dfs(i, adj, new HashSet<>()) - 1);
            }
            int minH = heights.get(0);
            for (int h : heights) {
                if (h < minH) {
                    minH = h;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < heights.size(); i++) {
                if (heights.get(i).equals(minH)) {
                    res.add(i);
                }
            }
            return res;
        }

        private int dfs(int i, Map<Integer, List<Integer>> adj, Set<Integer> visit) {
            if (!adj.containsKey(i)) return 0;
            visit.add(i);
            int temp = 0;
            for (int neighbor : adj.get(i)) {
                if (visit.contains(neighbor)) continue;
                temp = Math.max(temp, dfs(neighbor, adj, visit));
            }
            return 1 + temp;
        }
    }
}
