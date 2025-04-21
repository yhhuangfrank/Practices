package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class PathSum {

    public static void main(String[] args) {
        // root = [5,4,8,11,null,13,4,7,2,null,null,null,1],
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8, new TreeNode(13), null);
        TreeNode node3 = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        TreeNode node4 = new TreeNode(4, null, new TreeNode(1));
        node1.left = node3;
        node2.right = node4;
        root.left = node1;
        root.right = node2;
        System.out.println(solution.hasPathSum(root, 22));
    }

    static class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
//            return dfs(root, targetSum);
            return bfs(root, targetSum);
        }

        private boolean bfs(TreeNode root, int targetSum) {
            if (root == null) return false;

            Deque<Object[]> queue = new ArrayDeque<>(); // [TreeNode, curSum]
            queue.addLast(new Object[] {root, 0});
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Object[] arr = queue.pollFirst();
                    TreeNode node = (TreeNode) arr[0];
                    if (node == null) continue;
                    int sum = (int) arr[1];
                    if (node.left == null && node.right == null && sum + node.val == targetSum) return true;
                    if (node.left != null) {
                        queue.addLast(new Object[] {node.left, sum + node.val});
                    }
                    if (node.right != null) {
                        queue.addLast(new Object[] {node.right, sum + node.val});
                    }
                }
            }
            return false;
        }

        private boolean dfs(TreeNode root, int targetSum) {
            if (root == null) return false;
            if (root.left == null && root.right == null && targetSum == root.val) {
                return true;
            }
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }
}
