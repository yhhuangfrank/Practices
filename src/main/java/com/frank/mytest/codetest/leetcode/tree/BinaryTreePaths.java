package com.frank.mytest.codetest.leetcode.tree;

import java.util.*;

public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2, null, new TreeNode(5));
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        Solution solution = new Solution();
        System.out.println(solution.binaryTreePaths(root)); // ["1->2->5","1->3"]
    }

    static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, new ArrayDeque<>(), res);
            return res;
        }

        private void dfs(TreeNode root, Deque<String> stack, List<String> res) {
            if (root == null) return;
            if (root.left == null && root.right == null) {
                stack.addLast(String.valueOf(root.val));
                res.add(String.join("->", stack));
                stack.pollLast();
                return;
            }
            stack.addLast(String.valueOf(root.val));
            dfs(root.left, stack, res);
            dfs(root.right, stack, res);
            stack.pollLast();
        }
    }
}
