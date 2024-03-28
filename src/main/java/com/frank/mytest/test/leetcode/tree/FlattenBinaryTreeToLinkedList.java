package com.frank.mytest.test.leetcode.tree;

/**
 * 給定一個 Binary Tree 攤平成 "LinkedList" (每個節點都只有右節點，左節點皆為 null)
 * LinkedList 順序會與 BT 的 pre-order 相同
 */
public class FlattenBinaryTreeToLinkedList {

    static TreeNode prev;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(5, null, new TreeNode(6));
        TreeNode root = new TreeNode(1, left, right);
//        flatten(root);
        flattenV2(root);
        TreeNode temp = root;
        while (temp != null) {
            System.out.print(temp);
            System.out.println(", temp.left: " + temp.left);
            temp = temp.right;
        }
    }

    /**
     * 對於每個子樹，
     * 將左子樹攤平後的根節點 (l)
     * 再把右子樹攤平後的根節點 (r) ，並將 l 接到根節點的右節點，r 則接在 l 的尾端
     */
    public static void flatten(TreeNode root) {
        dfs(root);
    }

    public static TreeNode dfs(TreeNode node) {
        if (node == null) return null;
        TreeNode l = null;
        TreeNode r = null;
        if (node.left != null) {
            l = dfs(node.left);
        }
        if (node.right != null) {
            r = dfs(node.right);
        }

        if (l != null) {
            TreeNode temp = l;
            while (temp.right != null) {
                temp = temp.right;
            }
            node.left = null; // 左節點皆置空
            node.right = l;
            temp.right = r;
        }

        return node;
    }

    /**
     * 解法二：使用一 field 紀錄左子樹攤平時最後遞迴到的節點
     * 將原有的右子樹接到此變數的右側，最後再攤平右子樹
     */
    public static void flattenV2(TreeNode root) {
        if (root == null) return;
        TreeNode l = root.left;
        TreeNode r = root.right;
        prev = root;
        flattenV2(root.left);
        root.left = null;
        root.right = l;
        prev.right = r;
        flattenV2(root.right);
    }
}
