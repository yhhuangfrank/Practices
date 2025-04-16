package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class EvaluateDivision {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> equations = new ArrayList<>();
        equations.add(List.of("a", "b"));
        equations.add(List.of("b", "c"));
        equations.add(List.of("bc", "cd"));
        double[] values = {1.5, 2.5, 5.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(List.of("a", "c"));
        queries.add(List.of("c", "b"));
        queries.add(List.of("bc", "cd"));
        queries.add(List.of("cd", "bc"));
        queries.add(List.of("x", "x"));
//        [3.75000,0.40000,5.00000,0.20000]
        System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
    }

    static class Solution {
        // O(N * M) time, N = queries.size, M = # of node in graph
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> adj = new HashMap<>(); // (node -> (neighbor, weight))
            int n = equations.size();
            for (int i = 0; i < n; i++) {
                List<String> pair = equations.get(i);
                String src = pair.get(0);
                String dst = pair.get(1);
                adj.putIfAbsent(src, new HashMap<>());
                adj.get(src).put(dst, values[i]);
                adj.putIfAbsent(dst, new HashMap<>());
                adj.get(dst).put(src, 1 / values[i]);
            }
            double[] res = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                res[i] = bfs(queries.get(i).get(0), queries.get(i).get(1), adj, new HashSet<>());
            }
            return res;
        }

        private double bfs(String node, String target, Map<String, Map<String, Double>> adj, Set<String> visit) {
            if (!adj.containsKey(node) || !adj.containsKey(target)) return -1.0;

            Deque<Object[]> queue = new ArrayDeque<>(); // queue of [node, weight]
            queue.addLast(new Object[] {node, 1.0});
            visit.add(node);

            while (!queue.isEmpty()) {
                Object[] arr = queue.pollFirst();
                String n = (String) arr[0];
                double w = (double) arr[1];
                if (target.equals(n)) return w;

                for (Map.Entry<String, Double> neighborMap : adj.get(node).entrySet()) {
                    String neighbor = neighborMap.getKey();
                    if (visit.contains(neighbor)) continue;
                    double weight = neighborMap.getValue();
                    visit.add(neighbor);
                    queue.addLast(new Object[] {neighbor, w * weight});
                }
            }
            return -1.0;
        }

        private double dfs(String node, String target, Map<String, Map<String, Double>> adj, Set<String> visit) {
            if (!adj.containsKey(node) || !adj.containsKey(target)) return -1.0;
            if (target.equals(node)) return 1.0;
            visit.add(node);

            for (Map.Entry<String, Double> neighborMap : adj.get(node).entrySet()) {
                String neighbor = neighborMap.getKey();
                if (visit.contains(neighbor)) continue;
                double val = neighborMap.getValue();
                double res = dfs(neighbor, target, adj, visit);
                if (res != -1.0) return val * res;
            }
            return -1.0;
        }
    }
}
