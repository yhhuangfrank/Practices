package com.frank.mytest.test.leetcode.tree;

/**
 * 對一個 BT， path sum 定義為 節點依照 edge 一路將相鄰的位置值相加，且至少要有一個節點
 * 找出給定的 BT 的最大 path sum
 */
public class BinaryTreeMaximumPathSum {
    static int max;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(-10, left, right);
        System.out.println(maxPathSum(root));
        max = 0;

        root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(maxPathSum(root));
        max = 0;

        root = null;
        System.out.println(maxPathSum(root));
        max = 0;

        root = new TreeNode(1);
        System.out.println(maxPathSum(root));
        max = 0;
    }

    /**
     * 對一個節點 n 作為子樹的根，經過 n 的路徑和有四種
     * 1) 自己一個
     * 2) 左子樹中最大路徑和 + 自己
     * 3) 右子樹中最大路徑和 + 自己
     * 4) 左、右子樹最大路徑和總和 + 自己
     * 其中 4) 的情況會無法往上傳遞到 n 的父節點 (因有分岔，不是一筆畫路徑)
     *
     * 使用 dfs 往上傳遞 1) - 3) 最大值 m，同時並計算 4) 的情況和目前最大值與 m 三者比較
     */
    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        max = root.val;
        dfs(root);
        return max;
    }

    public static int dfs(TreeNode node) {
        if (node == null) return 0;
        int l = node.left != null ? dfs(node.left) : 0;
        int r = node.right != null ? dfs(node.right) : 0;

        int tempMax = Math.max(l + node.val, r + node.val);
        tempMax = Math.max(tempMax, node.val);
        // 比 (node + l + r), tempMax 和目前 max 誰大並更新
        int noRootPathSum = Math.max(node.val + l + r, tempMax);
        max = Math.max(max, noRootPathSum);
        return tempMax; // 將情況 1-3 的最大值往上傳
    }
}
