package com.frank.mytest.codetest.leetcode.tree;

import lombok.*;


@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    int val;

    @ToString.Exclude
    TreeNode left;

    @ToString.Exclude
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }
}
