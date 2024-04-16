package com.frank.mytest.test.leetcode.grpah;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 給定一個 graph 的 edges ，回傳可以刪除的 edge 使得 graph 沒有環
 * 若有多個答案，回傳最後輸入的 edge
 */
public class RedundantConnection {

    private static class UnionFind {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();

        public boolean union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);

            if (p1 == p2) return false; // circle

            int r1 = rank.get(p1);
            int r2 = rank.get(p2);

            if (r1 < r2) {
                parent.put(p1, p2);
            } else if (r1 > r2) {
                parent.put(p2, p1);
            } else {
                parent.put(p2, p1);
                rank.put(p1, rank.get(p1) + 1);
            }
            return true;
        }

        public int find(int n) {
            int p = parent.get(n);
            while (p != parent.get(p)) {
                parent.put(p, parent.get(parent.get(p)));
                p = parent.get(p);
            }
            return p;
        }
    }


    public static void main(String[] args) {
        int[][] edges = new int[5][];
        edges[0] = new int[]{1, 2};
        edges[1] = new int[]{2, 3};
        edges[2] = new int[]{3, 4};
        edges[3] = new int[]{1, 4};
        edges[4] = new int[]{1, 5};
        System.out.println(Arrays.toString(findRedundantConnection(edges)));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind();
        for (int[] edge : edges) {
            unionFind.parent.putIfAbsent(edge[0], edge[0]);
            unionFind.parent.putIfAbsent(edge[1], edge[1]);
            unionFind.rank.putIfAbsent(edge[0], 0);
            unionFind.rank.putIfAbsent(edge[1], 0);
            if (!unionFind.union(edge[0], edge[1])) return edge;
        }
        return new int[2];
    }


}
