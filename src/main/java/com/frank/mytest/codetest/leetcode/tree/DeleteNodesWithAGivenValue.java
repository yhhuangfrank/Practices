package com.frank.mytest.codetest.leetcode.tree;

public class DeleteNodesWithAGivenValue {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2, new TreeNode(2), null);
        root.left = node1;
        node1.left = node2;
        Solution solution = new Solution();
        System.out.println(solution.removeLeafNodes(root, 2));
    }

    static class Solution {
        public TreeNode removeLeafNodes(TreeNode root, int target) {
            if (root == null) return null;
            root.left = removeLeafNodes(root.left, target);
            root.right = removeLeafNodes(root.right, target);
            if (root.left == null && root.right == null && root.val == target) {
                return null;
            }
            return root;
        }
    }
}
