package com.frank.mytest.test.dsa.tree;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BSTDemo {
    public static void main(String[] args) {
        BST bst = new BST(5);
        bst.insertNode(8);
        bst.insertNode(10);
        bst.insertNode(9);
        bst.insertNode(6);
        bst.insertNode(2);
        bst.insertNode(3);
//        bst.inOrderRecursively();
        System.out.print("preOrder: ");
        bst.preOrderIteratively();
        System.out.println();
        System.out.print("inOrder: ");
        bst.inOrderIteratively();
        System.out.print("postOrder: ");
        bst.postOrderIterativelyV2();

        bst.deleteNode(8);

        System.out.println("===========================");
//        bst.inOrderRecursively();
        System.out.print("preOrder: ");
        bst.preOrderIteratively();
        System.out.println();
        System.out.print("inOrder: ");
        bst.inOrderIteratively();
        System.out.print("postOrder: ");
        bst.postOrderIterativelyV2();
    }
}

class BST {
    TreeNode root;

    public BST(int val) {
        this.root = new TreeNode(val);
    }

    public void insertNode(int val) {
        this.root = insertNode(root, val);
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

    public TreeNode deleteNode(int val) {
        return deleteNode(root, val);
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

    public void preOrderRecursively() {
        preOrderRecursionHelper(root);
    }

    private void preOrderRecursionHelper(TreeNode node) {
        if (node == null) return;

        System.out.println(node.val);
        if (node.left != null) {
            preOrderRecursionHelper(node.left);
        }
        if (node.right != null) {
            preOrderRecursionHelper(node.right);
        }
    }

    // Time & Space O(n)
    public void preOrderIteratively() {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = this.root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.print(curr.val + " -> ");
                stack.addFirst(curr);
                curr = curr.left;
            } else {
                TreeNode node = stack.removeFirst();
                curr = node.right;
            }
        }
    }

    public void inOrderRecursively() {
        inOrderRecursionHelper(this.root);
    }

    private void inOrderRecursionHelper(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            inOrderRecursionHelper(node.left);
        }
        System.out.println(node.val);
        if (node.right != null) {
            inOrderRecursionHelper(node.right);
        }
    }

    public void inOrderIteratively() {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = this.root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            } else {
                TreeNode node = stack.removeFirst();
                System.out.println(node.val);
                curr = node.right;
            }
        }
    }

    public void postOrderRecursively() {
        postOrderRecursionHelper(this.root);
    }

    private void postOrderRecursionHelper(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            postOrderRecursionHelper(node.left);
        }
        if (node.right != null) {
            postOrderRecursionHelper(node.right);
        }
        System.out.println(node.val);
    }

    // 使用兩個 stack
    public void postOrderIterativelyV1() {
        if (this.root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Boolean> visit = new ArrayDeque<>();
        stack.addFirst(this.root);
        visit.addFirst(false);

        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            boolean isVisited = visit.removeFirst();
            if (isVisited) {
                System.out.print(node.val + " -> ");
            } else {
                stack.addFirst(node);
                visit.addFirst(true);
                if (node.right != null) {
                    stack.addFirst(node.right);
                    visit.addFirst(false);
                }
                if (node.left != null) {
                    stack.addFirst(node.left);
                    visit.addFirst(false);
                }
            }
        }
    }

    public void postOrderIterativelyV2() {
        if (this.root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        stack.addFirst(this.root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            list.addFirst(node.val);
            if (node.left != null) {
                stack.addFirst(node.left);
            }
            if (node.right != null) {
                stack.addFirst(node.right);
            }
        }
        System.out.println(list);
    }

    private TreeNode findMinNode(TreeNode node) {
        TreeNode curr = node;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
