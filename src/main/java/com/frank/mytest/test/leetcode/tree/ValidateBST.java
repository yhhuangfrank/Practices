package com.frank.mytest.test.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 給定一 BST，判斷是否為 BST
 * 核心：一棵合格 BST 使用 inOrder 會是有序排列
 */
public class ValidateBST {
    private static TreeNode lastNode; // 定義變量供遞迴使用

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode right = new TreeNode(7);
        TreeNode root = new TreeNode(4, left, right);

        System.out.println(isValidBSTByRecursion(root));
        System.out.println(isValidBSTByIteration(root));
    }

    /**
     *
     * 依照 in-order 想法，遞迴按照子樹的 左->根->右 ，lastNode 先代表子樹左節點，遍歷完後代表根節點，root 先代表目前根節點後代表右子樹節點
     */
    private static boolean isValidBSTByRecursion(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && !isValidBSTByRecursion(root.left)) return false; // 遞迴左子樹，檢查左子樹是否合格
        if (lastNode != null && root.val <= lastNode.val) return false; // lastNode != null 檢查是為了最左邊節點一開始 lastNode 是 null
        lastNode = root;
        return isValidBSTByRecursion(root.right); // 往右子樹遞迴，右子樹的值不可以比 lastNode 小
    }

    private static boolean isValidBSTByIteration(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null; // 定義比較用的節點 (上一次從 stack 中拿出來的節點)
        TreeNode tmp = root;

        while (!stack.isEmpty() || tmp != null) {// tmp == null 為 root 的檢查
            // 在任一子樹中一路往左子樹移動
            while (tmp != null) {
                stack.add(tmp);
                tmp = tmp.left;
            }
            // 找到最左邊節點，或是取得子樹的根節點或右節點
            tmp = stack.removeLast();
            // 關鍵：拿某一子樹的 1. 左節點與根節點比 2. 根節點與左節點比
            if (prev != null && tmp.val <= prev.val) return false;
            prev = tmp; // 紀錄此次節點
            tmp = tmp.right; // 往右子節點移動
        }
        return true; // 若檢查都沒問題則回傳 true
    }

}
