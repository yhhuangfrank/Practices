package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

/**
 * Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 * Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 * Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
 */
public class AllAncestorsOfANodeInADirectedAcyclicGraph {

    public static void main(String[] args) {
        int[][] edgeList1 = new int[][]{{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}};
        int[][] edgeList2 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
        Solution solution = new Solution();
        System.out.println(solution.getAncestors(8, edgeList1));
        System.out.println(solution.getAncestors(5, edgeList2));
    }

    static class Solution {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Set<Integer>> cache = new HashMap<>();

        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            for (int[] e : edges) {
                int child = e[1];
                int parent = e[0];
                adj.computeIfAbsent(child, k -> new ArrayList<>()).add(parent);
            }

            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> lst = new ArrayList<>(dfs(i));
                Collections.sort(lst);
                res.add(lst);
            }

            return res;
        }

        private Set<Integer> dfs(int i) {
            if (cache.containsKey(i)) return cache.get(i); // 已記錄過某一節點的祖先，直接返回
            Set<Integer> ancestors = new HashSet<>();
            for (int neighbor : adj.getOrDefault(i, Collections.emptyList())) {
                ancestors.add(neighbor);
                ancestors.addAll(dfs(neighbor));
            }
            cache.put(i, ancestors);
            return ancestors;
        }
    }
}
