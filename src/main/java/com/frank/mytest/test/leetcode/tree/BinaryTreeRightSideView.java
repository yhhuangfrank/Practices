package com.frank.mytest.test.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 想像站在 BT 的右側看，由上往下列出看到的節點值
 */
public class BinaryTreeRightSideView {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5, new TreeNode(3), new TreeNode(6));
        TreeNode left = new TreeNode(2, null, node);
        TreeNode right = new TreeNode(4);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(rightSideView(root));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            TreeNode last = null;
            for (int i = 0; i < count; i++) {
                last = queue.removeFirst();
                if (last.left != null) {
                    queue.add(last.left);
                }
                if (last.right != null) {
                    queue.add(last.right);
                }
            }

            res.add(last.val);
        }

        return res;
    }
}
