package com.frank.mytest.codetest.dsa.graph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 *        1
 *      /  \
 *     2 ㄧ 3
 *   / \   / \
 *  4  5  6  7
 *  \  /
 *   8
 */
public class GraphDemo2 {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        Node one = new Node("1");
        Node two = new Node("2");
        Node three = new Node("3");
        Node four = new Node("4");
        Node five = new Node("5");
        Node six = new Node("6");
        Node seven = new Node("7");
        Node eight = new Node("8");

        graph.addEdge(one,two);
        graph.addEdge(one,three);
        graph.addEdge(two,four);
        graph.addEdge(two,five);
        graph.addEdge(three, six);
        graph.addEdge(three, seven);
        graph.addEdge(four, eight);
        graph.addEdge(five, eight);
        System.out.println("numOfNodes: " + graph.numOfNodes);

        graph.dfs(one);
        graph.clearVisited();  // 清除 node 的 visited 資訊
        System.out.println("===============");
        graph.bfs(one);
    }

    private static class Graph {
        private final int numOfNodes;
        private final Set<Node> nodeSet = new HashSet<>();

        public Graph(int numOfNodes) {
            this.numOfNodes =  numOfNodes;
        }

        public void addEdge(Node node1, Node node2) {
            node1.neighbors.add(node2);
            node2.neighbors.add(node1);

            nodeSet.add(node1);
            nodeSet.add(node2);
        }

        public void clearVisited() {
            for (Node node : nodeSet) {
                node.isVisited = false;
            }
        }

        // 深度優先
        public void dfs(Node node) {
            node.isVisited = true;
            System.out.println(node);
            for (Node neighbor : node.neighbors) {
                if (!neighbor.isVisited) {
                    dfs(neighbor);
                }
            }
        }

        // 廣度優先
        public void bfs(Node node) {
            Deque<Node> queue = new ArrayDeque<>();
            queue.add(node);
            node.isVisited = true;
            Node temp;
            while (!queue.isEmpty()) {
                temp = queue.removeFirst();
                System.out.println(temp);
                for (Node neighbor : temp.neighbors) {
                    if (!neighbor.isVisited) {
                        queue.add(neighbor);
                        neighbor.isVisited = true;
                    }
                }
            }
        }
    }

    @Getter
    @Setter
    @ToString
    private static class Node {
        private String value;

        private boolean isVisited;

        @ToString.Exclude
        private List<Node> neighbors = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }
    }
}
