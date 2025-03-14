package com.frank.mytest.codetest.leetcode.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal2 {
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[15,7],[9,20],[3]]

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        root.left = node1;
        root.right = node2;
        Solution sol = new Solution();
        System.out.println(sol.levelOrderBottom(root));
    }

    static class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> res = new LinkedList<>();
            if (root == null) return res;
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                List<Integer> lst = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode n = queue.pollFirst();
                    lst.add(n.val);
                    if (n.left != null) {
                        queue.addLast(n.left);
                    }
                    if (n.right != null) {
                        queue.addLast(n.right);
                    }
                }
                res.addFirst(lst);
            }
            return res;
        }
    }
}
