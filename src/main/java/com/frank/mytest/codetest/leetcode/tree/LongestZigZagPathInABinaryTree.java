package com.frank.mytest.codetest.leetcode.tree;

public class LongestZigZagPathInABinaryTree {

    public static void main(String[] args) {
//        [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
//          1
//           \
//            1
//           / \
//          1   1
//             / \
//            1   1
//             \
//              1
//               \
//                1
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(1, null, new TreeNode(1));
        root.right = node1;
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.right = node6;

        Solution solution = new Solution();
        System.out.println(solution.longestZigZag(root));
    }

    static class Solution {
        public int maxLen = 0;

        public int longestZigZag(TreeNode root) {
            dfs(root, -1, 0);
            return this.maxLen - 1;
        }

        // 0: left, 1: right, -1: none; curLen: 目前節點數
        private void dfs(TreeNode root, int direction, int curLen) {
            if (root == null) {
                this.maxLen = Math.max(this.maxLen, curLen);
                return;
            }
            if (direction == 0) {
                dfs(root.left, 0, 1); // 從 left 再往左走, 節點數需重置
                dfs(root.right, 1, curLen + 1);
            } else if (direction == 1) {
                dfs(root.left, 0, curLen + 1);
                dfs(root.right, 1, 1);
            } else {
                dfs(root.left, 0, curLen + 1);
                dfs(root.right, 1, curLen + 1);
            }
        }
    }
}
