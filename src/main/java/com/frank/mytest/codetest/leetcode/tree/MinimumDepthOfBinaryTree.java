package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 找尋一棵 BT 的最短深度 (從 root 到葉子節點)
 */
public class MinimumDepthOfBinaryTree {
    static int minDep = 0;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(minDepth(root)); // 2
        System.out.println(minDepthV2(root)); // 2

        root = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(6);
        root.right = node1;
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        System.out.println(minDepth(root)); // 5
        minDep = 0;
        System.out.println(minDepthV2(root)); // 5
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 1; // first level
        int depth = 0;
        boolean isMinDepth = false;
        while (!queue.isEmpty()) {
            depth++;
            int currentCount = count;
            count = 0;
            for (int i = 0; i < currentCount; i++) { // 每次取目前這層的節點數
                TreeNode node = queue.removeFirst();
                if (node.left == null && node.right == null) { // leaf node
                    System.out.println("leaf node: " + node);
                    isMinDepth = true;
                    break;
                }
                if (node.left != null) {
                    queue.add(node.left);
                    count++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    count++;
                }
            }
            if (isMinDepth) {
                break;
            }
        }
        return depth;
    }

    // 使用 dfs 方式解
    public static int minDepthV2(TreeNode root) {
//        dfs(root, 1);
//        return minDep;
        return dfs(root);
    }

    public static void dfs(TreeNode root, int depth) {
         if (root == null) return;
         if (root.left == null && root.right == null) {
             if (minDep == 0) {
                 minDep = depth;
             } else {
                 minDep = Math.min(minDep, depth);
             }
             return;
         }
         if (root.left != null) {
             dfs(root.left, depth + 1);
         }
         if (root.right != null) {
             dfs(root.right, depth + 1);
         }
    }
    public static int dfs(TreeNode root) { // 回傳目前深度
         if (root == null) return 0;
         if (root.left == null || root.right == null) {
             // 若左右節點存在其中一個
             return Math.max(dfs(root.left), dfs(root.right)) + 1;
         }
         // 若左右節點存在
         return Math.min(dfs(root.left), dfs(root.right)) + 1;
    }
}
