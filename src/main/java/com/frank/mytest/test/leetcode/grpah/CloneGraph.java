package com.frank.mytest.test.leetcode.grpah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    public static void main(String[] args) {
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(1);
        GraphNode three = new GraphNode(1);
        GraphNode four = new GraphNode(1);

        one.neighbors.addAll(List.of(two, four));
        two.neighbors.addAll(List.of(one, three));
        three.neighbors.addAll(List.of(two, four));
        four.neighbors.addAll(List.of(one, three));
    }

    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;

        Map<Integer, GraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    public GraphNode dfs(GraphNode node, Map<Integer, GraphNode> map) {
        if (map.get(node.val) != null) return map.get(node.val);

        GraphNode newNode = new GraphNode(node.val);
        map.put(newNode.val, newNode);
        for (GraphNode n : node.neighbors) {
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }

    private static class GraphNode {
        int val;
        List<GraphNode> neighbors;

        public GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public GraphNode(int val, List<GraphNode> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }
}
