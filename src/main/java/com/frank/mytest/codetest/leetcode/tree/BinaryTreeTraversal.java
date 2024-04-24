package com.frank.mytest.codetest.leetcode.tree;

import java.util.*;

public class BinaryTreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> path = new ArrayList<>();
        // in-order
//        inOrderRecursively(root, path);
//        System.out.println(path);
//        path.clear();
//        inOrderIteratively(root, path);
//        System.out.println(path);
//        path.clear();
//        inOrderIterativelyV2(root, path);
//        System.out.println(path);
//        path.clear();

        // post-order
//        postOrderRecursively(root, path);
//        System.out.println(path);
//        path.clear();
        postOrderIteratively(root, path);
        System.out.println(path);
        path.clear();

        // pre-Order
        preOrderIteratively(root, path);
        System.out.println(path);

        // level-order
//        List<List<Integer>> levelPath = new ArrayList<>();
//        levelOrderRecursively(root, levelPath);
//        System.out.println(levelPath);
//
////        levelPath.clear();
//        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
//        TreeNode right = new TreeNode(3, new TreeNode(55, new TreeNode(99), null), null);
//        root = new TreeNode(1, left, right);
//        levelOrderIteratively(root, levelPath);
//        System.out.println(levelPath);

    }

    private static void postOrderIteratively(TreeNode root, List<Integer> path) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> result = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.addFirst(node.val); // 關鍵：加到 list 的最前方
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        path.addAll(result);
    }

    private static void postOrderRecursively(TreeNode root, List<Integer> path) {
        // 左右根
        if (root == null) return;
        if (root.left != null) {
            postOrderRecursively(root.left, path);
        }
        if (root.right != null) {
            postOrderRecursively(root.right, path);
        }
        path.add(root.val);
    }

    private static void levelOrderIteratively(TreeNode root, List<List<Integer>> levelPath) {
        if (root == null) return;
        // 宣告 queue 紀錄節點
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>(); // 儲存下層的節點值
            int currentLevelCount = queue.size();

            for (int i = 0; i < currentLevelCount; i++) { // 固定取出目前層的節點數
                TreeNode node = queue.poll();
                if (node == null) continue;

                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (!levelList.isEmpty()) {
                levelPath.add(levelList);
            }
        }
    }

    private static void levelOrderRecursively(TreeNode root, List<List<Integer>> levelPath) {
        recursionHelper(root, 0, levelPath);
    }

    private static void recursionHelper(TreeNode node, int level, List<List<Integer>> levelPath) {
        if (node == null) return;
        // 若目前 level 尚未有元素加入
        if (levelPath.size() == level) {
            levelPath.add(new ArrayList<>());
        }
        // 加入對應層數
        levelPath.get(level).add(node.val);

        // 遞迴到左右子節點
        recursionHelper(node.left, level + 1, levelPath);
        recursionHelper(node.right, level + 1, levelPath);
    }

    public static void inOrderRecursively(TreeNode root, List<Integer> path) {
        if (root == null) return;
        if (root.left != null) {
            inOrderRecursively(root.left, path);
        }
        path.add(root.val);
        if (root.right != null) {
            inOrderRecursively(root.right, path);
        }
    }

    public static void inOrderIteratively(TreeNode root, List<Integer> path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            path.add(root.val);
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode tmp = root;

        while (!stack.isEmpty() || tmp != null) {
            // 一路往左子樹移動
            while (tmp != null) {
                stack.add(tmp);
                tmp = tmp.left;
            }
            // 找到最左邊節點
            tmp = stack.removeLast();
            path.add(tmp.val);
            tmp = tmp.right; // 往右子節點移動
        }
    }

    public static void inOrderIterativelyV2(TreeNode root, List<Integer> path) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                path.add(node.val);  // Add after all left children
                p = node.right;
            }
        }
    }

    public static void preOrderIteratively(TreeNode root, List<Integer> path) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                path.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }
}
