package com.frank.mytest.test.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    /**
     *      1
     *    /    \
     *   2      2
     *  / \    / \
     *  4 3    3 4
     */
    public static void main(String[] args) {

        TreeNode one = new TreeNode(1);
        TreeNode two1 = new TreeNode(2);
        TreeNode two2 = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        TreeNode three2 = new TreeNode(3);
        TreeNode four1 = new TreeNode(4);
        TreeNode four2 = new TreeNode(4);

        one.left = two1;
        one.right = two2;

        two1.left = four1;
        two1.right =three1;

        two2.left = three2;
        two2.right = four2;

        System.out.println(isSymmetricRecursively(one));
        System.out.println(isSymmetricIteratively(one));

    }

    private static boolean isSymmetricRecursively(TreeNode root) {
        if (root == null) return true;
        // 鏡像對稱比較
        return checkSymmetric(root.left, root.right);
    }

    private static boolean isSymmetricIteratively(TreeNode root) {
        if (root == null) return true;

        // 設計一個 queue，將左右節點 (l, r) 放入
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        // 當 queue 中還有節點時，取出兩個節點進行比較，不相等則為 false
        while (!queue.isEmpty()) {
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();
            if (l == null && r == null) continue;
            if (l == null || r == null) return false;
            if (l.val != r.val) return false;

            // 若相等，則將 l.left, r.right, l.right, r.left 放入 queue
            queue.add(l.left);
            queue.add(r.right);
            queue.add(l.right);
            queue.add(r.left);
        }
        return true;
    }

    private static boolean checkSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true; // 兩邊都沒有值
        if (l == null || r == null) return false; // 其中一個為空
        if (l.val != r.val) return false; // 兩邊值不相等
        return checkSymmetric(l.left, r.right) && checkSymmetric(l.right, r.left);
    }
}