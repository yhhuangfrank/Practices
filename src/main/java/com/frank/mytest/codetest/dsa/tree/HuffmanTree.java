package com.frank.mytest.codetest.dsa.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTreeRootNode = createHuffmanTree(arr);
        huffmanTreeRootNode.preOrder();

    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int num : arr) {
            nodes.add(new Node(num));
        }
        nodes.sort(Comparator.comparingInt(Node::getValue)); // 從小到大排
        System.out.println(nodes);

        while (nodes.size() > 1) {
            nodes.sort(Comparator.comparingInt(Node::getValue)); // 從小到大排

            // 取出根節點權重最小的兩棵 BT
            // 1) 權重最小與第二小的節點
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 2) 組成一棵新的 BT
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            // 3) 從 nodes 移除處理過的節點
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 4) 將 parent 加入到 nodes 中，以做後續的進一步排序
            nodes.add(parent);
        }
        System.out.println(nodes);

        return nodes.get(0); // 返回根節點
    }

    @Getter
    @Setter
    @ToString
    public static class Node {
        private final int value; // 權值

        @ToString.Exclude
        private Node left;

        @ToString.Exclude
        private Node right;

        public void preOrder() {
            System.out.println(this.value);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
