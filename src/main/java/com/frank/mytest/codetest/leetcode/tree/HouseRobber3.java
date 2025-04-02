package com.frank.mytest.codetest.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3 {

    public static void main(String[] args) {
//        Input: root = [3,4,5,1,3,null,1]
//        Output: 9
//            3
//           / \
//          4   5
//         / \   \
//        1   3   1
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4, new TreeNode(1), new TreeNode(3));
        TreeNode node2 = new TreeNode(5, null, new TreeNode(1));
        root.left = node1;
        root.right = node2;
        Solution solution = new Solution();
        System.out.println(solution.rob(root));
    }

    static class Solution {
        public int rob(TreeNode root) {
//            return dfs(root);
//            return dfs(root, new HashMap<>());
            int[] res = dfsOptimal(root);
            return Math.max(res[0], res[1]);
        }
        // brute force
        private int dfs(TreeNode root, boolean isValid) {
            if (root == null) return 0;
            int res = dfs(root.left, true) + dfs(root.right, true);
            if (isValid) {
                int select = root.val + dfs(root.left, false) + dfs(root.right, false);
                res = Math.max(res, select);
            }
            return res;
        }

        // brute force
        private int dfs(TreeNode root) {
            if (root == null) return 0;

            int res = root.val;
            if (root.left != null) {
                res += dfs(root.left.left) + dfs(root.left.right);
            }
            if (root.right != null) {
                res += dfs(root.right.left) + dfs(root.right.right);
            }
            res = Math.max(res, dfs(root.left) + dfs(root.right));
            return res;
        }

        // memoization
        private int dfs(TreeNode root, Map<TreeNode, Integer> cache) {
            if (root == null) return 0;
            if (cache.containsKey(root)) return cache.get(root);

            int res = root.val;
            if (root.left != null) {
                res += dfs(root.left.left) + dfs(root.left.right);
            }
            if (root.right != null) {
                res += dfs(root.right.left) + dfs(root.right.right);
            }
            res = Math.max(res, dfs(root.left) + dfs(root.right));
            cache.put(root, res);
            return res;
        }

        // optimal recursion
        private int[] dfsOptimal(TreeNode root) {
            int[] res = new int[2]; // [withRoot, withoutRoot]
            if (root == null) return res;

            int[] left = dfsOptimal(root.left);
            int[] right = dfsOptimal(root.right);

            int withRoot = root.val + left[1] + right[1];
            int withoutRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

            return new int[] {withRoot, withoutRoot};
        }
    }
}
