package com.frank.mytest.codetest.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfProvinces {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] arr1 = new int[][] {{1,1,0}, {1,1,0}, {0,0,1}};
        int[][] arr2 = new int[][] {{1,0,0}, {0,1,0}, {0,0,1}};
        System.out.println(sol.findCircleNumV1(arr1));
        System.out.println(sol.findCircleNumV1(arr2));
        System.out.println(sol.findCircleNumV2(arr1));
        System.out.println(sol.findCircleNumV2(arr2));
    }
//    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//    Output: 2
//    Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//    Output: 3

    static class Solution {
        public int findCircleNumV2(int[][] isConnected) {
            int n = isConnected.length;
            int res = n;
            UnionFind uf = new UnionFind(n);
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (isConnected[r][c] == 1 && uf.union(r, c)) {
                        res -= 1;
                    }
                }
            }
            return res;
        }

        public int findCircleNumV1(int[][] isConnected) {
            int n = isConnected.length;
            Map<Integer, Set<Integer>> adj = new HashMap<>();
            for (int r = 0; r < n; r++) {
                adj.putIfAbsent(r, new HashSet<>());
                for (int c = 0; c < n; c++) {
                    if (isConnected[r][c] == 1 && r != c) {
                        adj.get(r).add(c);
                    }
                }
            }
            int res = 0;
            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i)) {
                    dfs(i, visited, adj);
                    res += 1;
                }
            }
            return res;
        }

        private void dfs(int i, Set<Integer> visited, Map<Integer, Set<Integer>> adj) {
            if (visited.contains(i)) return;
            visited.add(i);
            for (int n : adj.get(i)) {
                dfs(n, visited, adj);
            }
        }
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 0;
            }
        }

        private int find(int x) {
            int p = this.parent[x];
            while (p != this.parent[p]) {
                this.parent[p] = this.parent[this.parent[p]];
                p = this.parent[p];
            }
            return p;
        }

        private boolean union(int x, int y) {
            int p1 = this.find(x);
            int p2 = this.find(y);
            if (p1 == p2) return false;
            int r1 = this.rank[p1];
            int r2 = this.rank[p2];
            if (r1 <= r2) {
                this.rank[p2] += r2;
                this.parent[p1] = p2;
            } else {
                this.rank[p1] += r2;
                this.parent[p2] = p1;
            }
            return true;
        }
    }
}
