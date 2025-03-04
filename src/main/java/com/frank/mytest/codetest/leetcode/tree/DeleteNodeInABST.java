package com.frank.mytest.codetest.leetcode.tree;

public class DeleteNodeInABST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode three = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        TreeNode six = new TreeNode(6, null, new TreeNode(7));
        root.left = three;
        root.right = six;
        Solution sol = new Solution();
        System.out.println(sol.deleteNode(root, 5));
    }

    static class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;

            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;

                TreeNode minNode = findMinNode(root.right);
                if (minNode != null) {
                    root.val = minNode.val;
                    root.right = deleteNode(root.right, minNode.val);
                }
            }
            return root;
        }

        private TreeNode findMinNode(TreeNode root) {
            TreeNode temp = root;
            while (temp != null && temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }
    }
}
