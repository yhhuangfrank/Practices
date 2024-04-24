package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 給定一個 BT，找到某一層其各節點總和為最大值
 */
public class MaximumLevelSumOfABinaryTree {
    public static void main(String[] args) {
        TreeNode l = new TreeNode(-200, new TreeNode(-20), new TreeNode(-5));
        TreeNode r = new TreeNode(-300, new TreeNode(-10), null);
        TreeNode root = new TreeNode(-100, l, r);
        System.out.println(maxLevelSum(root));
    }

    public static int maxLevelSum(TreeNode root) {
        if (root == null) return -1;
        int level = 1;
        int maxLevel = 1;
        int max = root.val;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            int tempMax = 0;
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.removeFirst();
                tempMax += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (tempMax > max) {
                max = tempMax;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;
    }
}
