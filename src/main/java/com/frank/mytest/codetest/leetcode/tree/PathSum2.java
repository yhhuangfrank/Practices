package com.frank.mytest.codetest.leetcode.tree;

import java.util.*;

public class PathSum2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8, new TreeNode(13), null);
        TreeNode node3 = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        TreeNode node4 = new TreeNode(4, new TreeNode(5), new TreeNode(1));
        node1.left = node3;
        node2.right = node4;
        root.left = node1;
        root.right = node2;
        System.out.println(solution.pathSum(root, 22)); // [[5, 4, 11, 2], [5, 8, 4, 5]]
    }

    static class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, targetSum, new ArrayDeque<>(), res);
            return res;
        }

        private void dfs(TreeNode root, int targetSum, Deque<Integer> stack, List<List<Integer>> res) {
            if (root == null) return;
            // leaf node
            if (root.left == null && root.right == null) {
                if (root.val == targetSum) {
                    stack.addLast(root.val);
                    res.add(new ArrayList<>(stack));
                    stack.pollLast();
                }
                return;
            }
            stack.addLast(root.val);
            dfs(root.left, targetSum - root.val, stack, res);
            dfs(root.right, targetSum - root.val, stack, res);
            stack.pollLast();
        }
    }
}
