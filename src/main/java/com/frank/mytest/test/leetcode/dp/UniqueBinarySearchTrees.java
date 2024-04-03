package com.frank.mytest.test.leetcode.dp;

/**
 * 給定一非零正整數 n ，找出節點值為 1 ~ n 組成的 BST 共有多少棵？
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    /**
     * BST 的子樹依舊會是 BST
     * n 個值裡選一個數做 root
     * 考慮其中某一 1<= i <= n 的時候
     * root 左子樹可能會有 0 ~ i-1 個節點，若左子樹有 j 個節點
     * --> 相當於左子樹是由 1 ~ j 的連續值組成的 BST
     * root 右子樹則會有 i - 1 - j 個節點
     * --> 相當於右子樹是由 j+2 ~ n 的連續值組成的 BST
     * 左右可能性相乘，並且 j 要從 0 ~ i-1
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1]; // 0 個也算一種
        // 初始化 0 個和 1 個節點情況
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < i; j++) { // 在 i 個節點狀況下，左子樹有 j 個節點 (j < i)，相當於找了左右子樹節點個數的組合
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }
}
