package com.frank.mytest.test.leetcode.tree;


/**
 * 給定一棵 BST 的根節點，與想查詢的值
 * 返回對應值的節點所在子樹的根節點
 */
public class SearchInBST {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode right = new TreeNode(7);
        TreeNode root = new TreeNode(4, left, right);
        TreeNode node = searchBST(root, 2);
        System.out.println(node);
    }

    public static TreeNode searchBST(TreeNode root, int val) {
//        return searchRecursively(root,val);
        return searchIteratively(root,val);
    }

    public static TreeNode searchRecursively(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (val < root.val && root.left != null) {
            return searchRecursively(root.left,val);
        }
        if (val > root.val && root.right != null) {
            return searchRecursively(root.right, val);
        }
        return null;
    }

    public static TreeNode searchIteratively(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;

        TreeNode temp = root;

        while (temp != null) {
            if (temp.val == val) {
                return temp;
            } else if (val < temp.val && temp.left != null) {
                temp = temp.left;
            } else if (val > temp.val && temp.right != null) {
                temp = temp.right;
            } else {
                temp = null;
            }
        }
        return null;
    }
}
