package com.frank.mytest.test.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 給一個 BST 的 pre-order traversal 陣列，構建 BST 並返回其 root
 */
public class ConstructBinarySearchTreeFromPreOrderTraversal {
    public static void main(String[] args) {
        int[] preOrder = new int[]{8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder(preOrder);
        System.out.println(root);
        preOrder(root);
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            TreeNode parent = root;
            TreeNode temp = root;
            while (temp != null) {
                parent = temp;
                if (node.val < temp.val) {
                    temp = temp.left;
                } else if (node.val > temp.val) {
                    temp = temp.right;
                }
            }
            if (node.val < parent.val) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        return root;
    }

    public static void preOrder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                System.out.println(node.val);
                stack.addFirst(node);
                node = node.left;
            } else {
                node = stack.removeFirst();
                node = node.right;
            }
        }

    }
}
