package com.frank.mytest.codetest.leetcode.tree;

public class TrimABinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(3, null, new TreeNode(4));
        TreeNode node1 = new TreeNode(0, null, new TreeNode(2));
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(1);
        root.left = node1;
        node2.left = node3;
        System.out.println(solution.trimBST(root, 1, 3));
    }

    static class Solution {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) return null;
            if (root.val > high) { // 去掉右子樹
                return trimBST(root.left, low, high);
            }
            if (root.val < low) { // 去掉左子樹
                return trimBST(root.right, low, high);
            }
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
