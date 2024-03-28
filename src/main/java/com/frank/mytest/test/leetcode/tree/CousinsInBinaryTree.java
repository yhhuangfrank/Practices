package com.frank.mytest.test.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 給定一個 binary tree，與兩個 TreeNode 的 val，確認兩者是否為 "cousins"
 * cousin 定義為"相同深度"但"不同父節點"的兩個節點
 * 並且每個節點的值不重複
 */
public class CousinsInBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), null);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isCousinsV2(root, 4, 3)); // false

        left = new TreeNode(2, null, new TreeNode(4));
        right = new TreeNode(3);
        root = new TreeNode(1, left, right);
        System.out.println(isCousinsV2(root, 2, 3));
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
        int levelCount = 1;
        Deque<TreeNode[]> queue = new ArrayDeque<>();
        queue.add(new TreeNode[]{root, null});
        while (!queue.isEmpty()) {
            int currentLevelCount = levelCount;
            levelCount = 0;
            int cousins = 0;
            for (int i = 0; i < currentLevelCount; i++) {
                TreeNode[] nodes = queue.removeFirst();
                TreeNode l = nodes[0];
                TreeNode r = nodes[1];
                boolean isSameParent = l != null && r != null && ((l.val == x && r.val == y) || (l.val == y && r.val == x));
                for (TreeNode n : nodes) {
                    if (n == null) continue;
                    if (n.val == x || n.val == y) {
                        cousins++;
                    }
                    if (n.left != null || n.right != null) {
                        queue.add(new TreeNode[]{n.left, n.right});
                        levelCount++;
                    }
                }
                if (isSameParent) {
                    cousins = 0; // 要重算
                }
                if (cousins == 2) return true;
            }
        }
        return false;
    }

    public static boolean isCousinsV2(TreeNode root, int x, int y) {
        if (root == null) return false;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode parent = null;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // 取出同一層節點
                TreeNode node = queue.removeFirst();
                TreeNode l = node.left;
                TreeNode r = node.right;
                boolean isAnyMatch = (l != null && (l.val == x || l.val == y))
                        || (r != null && (r.val == x || r.val == y)); // 左右子節點有符合 x or y
                if (isAnyMatch) {
                    if (parent == null) {
                        parent = node;
                    } else {
                        return parent != node; // 找到的兩個節點必須是不同 parent
                    }
                }
                if (l != null) {
                    queue.add(l);
                }
                if (r != null) {
                    queue.add(r);
                }
            }
            if (parent != null) return false; // 若同一層找完只有找到其中一個，代表一定不為 cousins
        }
        return false;
    }
}
