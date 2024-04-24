package com.frank.mytest.codetest.dsa.graph;

import java.util.*;

public class DirectGraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        System.out.println(graph.hasPath(1, 3));
        System.out.println(graph.hasPath(3, 1));
        System.out.println(graph.removeEdge(1, 2));
        System.out.println(graph.hasPath(1, 3));
    }
}

class Graph {
    private final Map<Integer, Node> nodes;
    private final Set<Integer> visited;

    public Graph() {
        this.nodes = new HashMap<>();
        this.visited = new HashSet<>();
    }

    public void addEdge(int src, int dst) {
        this.nodes.putIfAbsent(src, new Node(src));
        this.nodes.putIfAbsent(dst, new Node(dst));
        Node srcNode = this.nodes.get(src);
        Node dstNode = this.nodes.get(dst);
        srcNode.neighbors.add(dstNode.val);
    }

    public boolean removeEdge(int src, int dst) {
        Node srcNode = nodes.get(src);
        Node dstNode = nodes.get(dst);
        if (srcNode == null || dstNode == null) return false;
        srcNode.neighbors.remove(dst);
        return true;
    }

    public boolean hasPath(int src, int dst) {
        Node srcNode = nodes.get(src);
        if (srcNode == null) return false;
        boolean isPathExist = bfs(srcNode, dst);
        visited.clear();
        return isPathExist;
    }

    private boolean dfs(Node srcNode, int dst) {
        if (srcNode.val == dst)
            return true;
        visited.add(srcNode.val);
        for (Integer i : srcNode.neighbors) {
            Node n = nodes.get(i);
            if (!visited.contains(n.val) && dfs(n, dst)) {
                return true;
            }
        }
        return false;
    }

    private boolean bfs(Node srcNode, int dst) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(srcNode);
        visited.add(srcNode.val);
        while (!queue.isEmpty()) {
            Node n = queue.removeFirst();
            for (Integer nei : n.neighbors) {
                if (nei == dst) return true;
                Node node = nodes.get(nei);
                if (!visited.contains(nei)) {
                    queue.add(node);
                }
            }
        }
        return false;
    }

    private static class Node {
        int val;
        Set<Integer> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new HashSet<>();
        }
    }
}

