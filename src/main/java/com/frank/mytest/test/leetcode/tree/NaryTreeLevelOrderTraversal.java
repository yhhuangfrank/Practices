package com.frank.mytest.test.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 有一多路子樹，(每個節點有多個子節點)
 * 輸出 level-order 遍歷，每一 level 放在同一個 list 中
 */
public class NaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Node left = new Node(3, List.of(new Node(5), new Node(6)));
        Node middle = new Node(2);
        Node right = new Node(4);
        Node root = new Node(1, List.of(left, middle, right));
        List<List<Integer>> list = levelOrder(root);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < levelCount; i++) {
                Node head = queue.removeFirst();
                list.add(head.val);
                if (head.children != null && !head.children.isEmpty()) {
                    queue.addAll(head.children);
                }
            }
            res.add(list);
        }

        return res;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
};
