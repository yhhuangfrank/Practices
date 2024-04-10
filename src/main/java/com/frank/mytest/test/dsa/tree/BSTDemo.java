package com.frank.mytest.test.dsa.tree;


public class BSTDemo {
    public static void main(String[] args) {
        BST bst = new BST(5);
        bst.insertNode(8);
        bst.insertNode(10);
        bst.insertNode(9);
        bst.insertNode(6);
        bst.insertNode(2);
        bst.insertNode(3);
        bst.inOrder();
        System.out.println("===========================");
        bst.deleteNode(8);
        bst.inOrder();
    }
}

class BST {
    TreeNode root;

    public BST(int val) {
        this.root = new TreeNode(val);
    }

    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = insertNode(root.right, val);
        } else if (val < root.val) {
            root.left = insertNode(root.left, val);
        }
        return root;
    }

    public TreeNode insertNode(int val) {
        return insertNode(root, val);
    }

    private TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) return null;

        if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else {
            // 0 or 1 child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // 2 children
            TreeNode minNode = findMinNode(root.right);
            if (minNode != null) {
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val); // 移除 minNode
            }
        }
        return root;
    }

    public TreeNode deleteNode(int val) {
        return deleteNode(root, val);
    }

    private void preOrder(TreeNode node) {
        if (node == null) return;

        System.out.println(node.val);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            inOrder(node.left);
        }
        System.out.println(node.val);
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private TreeNode findMinNode(TreeNode node) {
        TreeNode curr = node;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
