package com.frank.mytest.codetest.leetcode.grpah;


import java.util.*;

//Input
//["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
//        [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
//Output
//[null, 6, -1, null, 6]
public class DesignGraphWithShortestPathCalculator {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0, 2, 5}, {0, 1, 2}, {1, 2, 1}, {3, 0, 3}};
        System.out.println("Floyd-Warshall algorithm...");
        Graph1 graph1 = new Graph1(n, edges);
        System.out.println(graph1.shortestPath(3, 2));
        System.out.println(graph1.shortestPath(0, 3));
        graph1.addEdge(new int[]{1, 3, 4});
        System.out.println(graph1.shortestPath(0, 3));

        System.out.println("Dijkstra algorithm...");
        Graph2 graph2 = new Graph2(n, edges);
        System.out.println(graph2.shortestPath(3, 2));
        System.out.println(graph2.shortestPath(0, 3));
        graph2.addEdge(new int[]{1, 3, 4});
        System.out.println(graph2.shortestPath(0, 3));
    }

    /**
     * Floyd–Warshall，小 node 數量時適用，較快
     */
    static class Graph1 {
        int[][] dist;
        int n;

        public Graph1(int n, int[][] edges) {
            // 初始化
            this.n = n;
            this.dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        this.dist[i][j] = 0;
                    } else {
                        this.dist[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            // 添加 src -> dst 的距離
            for (int[] e : edges) {
                int src = e[0];
                int dst = e[1];
                int cost = e[2];
                this.dist[src][dst] = cost;
            }
            // 對於任意 i -> j 的路徑，都可以嘗試先從 i 到另外一個點 k，再走到 j
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int path1 = this.dist[i][k];
                        int path2 = this.dist[k][j];
                        int sum;
                        if (path1 == Integer.MAX_VALUE || path2 == Integer.MAX_VALUE) {
                            sum = Integer.MAX_VALUE;
                        } else {
                            sum = path1 + path2;
                        }
                        this.dist[i][j] = Math.min(this.dist[i][j], sum);
                    }
                }
            }
        }

        // 每加入一個邊，只需要更新會走到那個邊上的路線，重新計算最短路徑
        public void addEdge(int[] edge) {
            int src = edge[0];
            int dst = edge[1];
            int cost = edge[2];
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    int path1 = this.dist[i][src];
                    int path2 = this.dist[dst][j];
                    int sum;
                    if (path1 == Integer.MAX_VALUE || path2 == Integer.MAX_VALUE) {
                        sum = Integer.MAX_VALUE;
                    } else {
                        sum = path1 + cost + path2;
                    }
                    this.dist[i][j] = Math.min(this.dist[i][j], sum);
                }
            }
        }

        public int shortestPath(int node1, int node2) {
            return this.dist[node1][node2] == Integer.MAX_VALUE ? -1 : this.dist[node1][node2];
        }
    }

    /**
     * Dijkstra
     * 適用於 Node 數量較多情形
     */
    static class Graph2 {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        public Graph2(int n, int[][] edges) {
            for (int i = 0; i < n; i++) {
                this.adj.put(i, new ArrayList<>());
            }
            for (int[] e : edges) {
                this.addEdge(e);
            }
        }

        public void addEdge(int[] edge) {
            int src = edge[0];
            int dst = edge[1];
            int cost = edge[2];
            this.adj.get(src).add(new int[] {cost, dst});
        }

        public int shortestPath(int node1, int node2) {
            int res = -1;
            if (!this.adj.containsKey(node1) || !this.adj.containsKey(node2)) {
                return res;
            }
            Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            minHeap.add(new int[] {0, node1});

            int[] shortest = new int[this.adj.size()];
            Arrays.fill(shortest, Integer.MAX_VALUE); // 初始化 node1 到每個點的最短距離
            shortest[node1] = 0; // 到自己距離是 0

            while (!minHeap.isEmpty()) {
                int[] poll = minHeap.poll();
                int cost = poll[0];
                int node = poll[1];
                shortest[node] = cost;
                if (node == node2) {
                    return cost; // 走到終點
                }
                for (int[] neighbor : this.adj.get(node)) {
                    int cost2 = neighbor[0] + cost;
                    int neighborNode = neighbor[1];
                    if (cost2 > shortest[neighborNode]) continue; // 若距離比較遠，不放入 heap，節省放入的時間複雜度
                    minHeap.add(new int[] {cost2, neighborNode});
                }
            }
            return res;
        }
    }
}
