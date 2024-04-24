package com.frank.mytest.codetest.leetcode.tree;

import java.util.LinkedList;

public class MergeTwoTrees {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3, new TreeNode(5), null);
        root1.right = new TreeNode(2);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1, null, new TreeNode(4));
        root2.right = new TreeNode(3, null, new TreeNode(7));

//        TreeNode merged = mergeRecursively(root1, root2);
        TreeNode merged = mergeIteratively(root1, root2);
        merged.inOrder();
    }

    public static TreeNode mergeRecursively(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeRecursively(root1.left, root2.left);
        root1.right = mergeRecursively(root1.right, root2.right);
        return root1;
    }

    public static TreeNode mergeIteratively(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;

        TreeNode[] treeNodes = new TreeNode[]{root1, root2};
        // 使用 stack 紀錄每組需要合併的節點
        LinkedList<TreeNode[]> stack = new LinkedList<>(); // 先將最初兩個加入
        stack.add(treeNodes);
        while (!stack.isEmpty()) {
            TreeNode[] pair = stack.removeLast();
            TreeNode node1 = pair[0];
            TreeNode node2 = pair[1];
            if (node2 == null) continue; // 第二棵樹有節點才要加入並到第一棵樹
            node1.val += node2.val; // 合併值
            // 檢查 node1 左右子節點
            if (node1.left != null) {
                stack.add(new TreeNode[]{node1.left, node2.left});
            } else {
                node1.left = node2.left;
            }
            if (node1.right != null) {
                stack.add(new TreeNode[]{node1.right, node2.right});
            } else {
                node1.right = node2.right;
            }
        }
        return root1;
    }
}
