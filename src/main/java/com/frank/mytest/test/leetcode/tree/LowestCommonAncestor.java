package com.frank.mytest.test.leetcode.tree;

/**
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 */
public class LowestCommonAncestor {
    static TreeNode lca = null;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode left = new TreeNode(5, new TreeNode(6), node1);
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4)));
    }

    /**
     * 設定每個 node都是獨立不重複的值，並且 p, q 必定存在在樹中
     * 找 LCA (Lowest Common Ancestor) 時可能為 3 種情況
     * 1) p,q 為左右子樹中的節點
     * 2) p為某一子樹根節點, q 為其左子樹節點
     * 3) 與情況 2 相反
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return lca;
    }

    /**
     * 對一子樹根節點 n 而言，dfs 過程:
     * 若 n 為 null, 回傳 false
     * 若遍歷左子樹 dfs(n.left, p, q) 得到 true，標記為 l = 1 (說明左子樹中有 p,q 任一節點)
     * 若遍歷左子樹 dfs(n.right, p, q) 得到 true，標記為 r = 1 (說明左子樹中有 p,q 任一節點)
     * 若 n 為 p,q 任一節點，標記 mid = 1 (說明自身為p,q之一，也符合 LCA要找的目標)
     * 若 l+r+mid == 2 -> 說明達成上述 LCA 的三種情形，此時 n 即為 LCA
     * 回傳 l+r+mid > 0 作為返回結果，若 > 0 表示此子樹找到 p, q 任一或任兩個節點
     */
    public static boolean dfs(TreeNode n, TreeNode p, TreeNode q) {
        if (n == null) return false;
        int l = dfs(n.left, p, q) ? 1 : 0;
        int r = dfs(n.right, p, q) ? 1 : 0;
        int mid = (n.val == p.val || n.val == q.val) ? 1 : 0;
        if (l + r + mid == 2) {
            lca = n;
        }
        return l + r + mid > 0;
    }
}
