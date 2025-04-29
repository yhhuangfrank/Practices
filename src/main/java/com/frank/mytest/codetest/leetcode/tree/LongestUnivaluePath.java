package com.frank.mytest.codetest.leetcode.tree;

/**
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of the path between two nodes is represented by the number of edges between them.
 * <p>
 * 找到一棵 BT 中，具有相同節點值的最長路徑 (不一定有經過 root 節點)
 * 路徑計算為兩個 node 經過一 edge 為計算單位
 * <p>
 * 一條具有 univalue path 的狀況
 * 1) 目前的節點是路徑中最上面的值 -> 找出往左或往右的最長路徑 (值相同)，再加上路徑最上方節點即可得到總路徑長
 * 2) 目前節點不是路徑中最上面的值 -> 有一邊必須捨棄
 * 取往左邊/右邊的最長路徑的較大值 + 1，作為往上回傳的長度
 * 3) 取得一條路徑長時，跟全局最大值比較並更新
 */
public class LongestUnivaluePath {
    static int maxLength = 0;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(4), new TreeNode(4));
        TreeNode right = new TreeNode(5, null, new TreeNode(5));
        TreeNode root = new TreeNode(1, left, right);
        Solution solution = new Solution();
//        System.out.println(solution.longestUnivaluePath(root)); // 2
        System.out.println(solution.longestUnivaluePathV2(root)); // 2
    }

    static class Solution {
        public int longestUnivaluePathV2(TreeNode root) {
            int[] res = new int[1];
            dfs(root, null, res);
            return res[0];
        }

        private int dfs(TreeNode root, Integer value, int[] res) {
            if (root == null) return 0;
            int left = dfs(root.left, root.val, res);
            int right = dfs(root.right, root.val, res);
            res[0] = Math.max(res[0], left + right);

            if (value != null && value == root.val) {
                return 1 + Math.max(left, right);
            }
            return 0;
        }

        public int longestUnivaluePath(TreeNode root) {
            if (root == null) return 0;
            countLength(root, null);
//        System.out.println("len " + length);
            return maxLength;
        }

        /**
         * @param root  目前節點
         * @param value 父節點的值
         * @return 以父節點作為相同值的最大路徑長
         */
        public static int countLength(TreeNode root, Integer value) {
            if (root == null) return 0;
            // 數左右分支各自長度
            int left = countLength(root.left, root.val);
            int right = countLength(root.right, root.val);
            maxLength = Math.max(maxLength, left + right); // 取和之後跟最大長比較與更新
            System.out.println(maxLength + " , root: " + root + ", value: " + value);

            if (value != null && root.val == value) { // 若目前節點與上層節點相同值，表示可以往上連接，長度再加一
                return Math.max(left, right) + 1; // 取左右路徑較長的分支
            }
            return 0; // 無法往上連接
        }
    }


}
