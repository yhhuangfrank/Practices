package com.frank.mytest.codetest.leetcode.tree;

public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3);
        System.out.println(solution.diameterOfBinaryTree(root)); // 3
    }

    static class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            int[] res = new int[1];
            dfs(root, res);
            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {
            if (root == null) return 0;

            int left = dfs(root.left, res);
            int right = dfs(root.right, res);
            int len = 1 + Math.max(left, right);
            res[0] = Math.max(res[0], Math.max(len, left + right));
            return len;
        }
    }
}
