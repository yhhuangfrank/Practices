package com.frank.mytest.codetest.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

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
        Solution solution = new Solution();
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
        System.out.println(solution.pathSum(root, 8)); // 3
        System.out.println(solution.pathSumV2(root, 8)); // 3
    }

    static class Solution {
        // brute force, O(N^2) time
        public int pathSum(TreeNode root, int targetSum) {
            int[] res = new int[1];
            dfs(root,targetSum, res); // dfs 走遍所有節點
            return res[0];
        }

        private void dfs(TreeNode root, int targetSum, int[] res) {
            if (root == null) {
                return;
            }
            test(root, targetSum, res); // 測試從此節點為root是否有合格的解
            // 查看子樹當作起點的情況
            dfs(root.left, targetSum, res);
            dfs(root.right, targetSum, res);
        }

        private void test(TreeNode root, int targetSum, int[] res) {
            if (root == null) return;
            if (root.val == targetSum) {
                res[0] += 1;
            }
            test(root.left, targetSum - root.val, res); // 新的 target = targetSum - root.val
            test(root.right, targetSum - root.val, res);
        }

        // 使用map儲存路徑和的方法數, O(N) 遍歷每個node一遍
        private int pathSumV2(TreeNode root, int targetSum) {
            int[] res = new int[1]; // 儲存總共方法數
            helper(root,0L, targetSum, new HashMap<>(), res);
            return res[0];
        }

        private void helper(TreeNode root, long curSum, int targetSum, Map<Long,Integer> cache, int[] res) {
            if (root == null) return;
            curSum += root.val;

            if (curSum == targetSum) {
                res[0] ++;
            }
            res[0] += cache.getOrDefault(curSum - targetSum, 0); // 加上已計算過的總和
            // 儲存目前總和方法數
            cache.put(curSum, cache.getOrDefault(curSum, 0) + 1);
            helper(root.left, curSum, targetSum, cache, res);
            helper(root.right, curSum, targetSum, cache, res);
            cache.put(curSum, cache.get(curSum) - 1); // 回溯上一層時需方法數不再適用，減少一次
        }
    }
}
