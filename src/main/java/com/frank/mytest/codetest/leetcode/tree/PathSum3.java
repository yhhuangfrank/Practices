package com.frank.mytest.codetest.leetcode.tree;

public class PathSum3 {

    public static void main(String[] args) {
//        Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//        Output: 3
//           10
//          / \
//         5  -3
//        / \   \
//        3  2   11
//       / \  \
//      3  -2  1
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;
        System.out.println(pathSum(root, 8)); // 3
    }
    // brute force, O(N^2) time
    public static int pathSum(TreeNode root, int targetSum) {
        int[] res = new int[1];
        dfs(root,targetSum, res); // dfs 走遍所有節點
        return res[0];
    }

    public static void dfs(TreeNode root, int targetSum, int[] res) {
        if (root == null) {
            return;
        }
        test(root, targetSum, res);
        dfs(root.left, targetSum, res);
        dfs(root.right, targetSum, res);
    }

    public static void test(TreeNode root, int targetSum, int[] res) {
        if (root == null) return;
        if (root.val == targetSum) {
            res[0] += 1;
        }
        test(root.left, targetSum - root.val, res); // 新的 target = targetSum - root.val
        test(root.right, targetSum - root.val, res);
    }
}
