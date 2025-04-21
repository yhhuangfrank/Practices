package com.frank.mytest.codetest.leetcode.tree;

public class BinaryTreePruning {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(0, new TreeNode(0), new TreeNode(1));
        root.right = right;
        System.out.println(right.left); // TreeNode(val=0)
        System.out.println(solution.pruneTree(root)); // TreeNode(val=1)
        System.out.println(right.left); // null
        System.out.println(solution.pruneTree(new TreeNode(0))); // null (edge case)
    }

    static class Solution {
        public TreeNode pruneTree(TreeNode root) {
            TreeNode dummy = new TreeNode(-1); // for edge case (root need to be deleted)
            dummy.left = root;
            dfs(dummy);
            return dummy.left;
        }

        private boolean dfs(TreeNode root) {
            if (root == null) return false;
            boolean left = dfs(root.left);
            boolean right = dfs(root.right);
            if (!left) {
                root.left = null;
            }
            if (!right) {
                root.right = null;
            }
            return root.val == 1 || left || right;
        }
    }
}
