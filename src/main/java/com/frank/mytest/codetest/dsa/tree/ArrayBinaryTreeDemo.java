package com.frank.mytest.codetest.dsa.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("pre-order =====>");
        tree.preOrder(); // 從 root 節點
        System.out.println("in-order =====>");
        tree.inOrder();
        System.out.println("post-order =====>");
        tree.postOrder();
    }

}

class ArrayBinaryTree {
    private int[] arr; // 儲存節點用

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0); // 從 root 節點開始
    }

    public void inOrder() {
        this.inOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    // pre-order 遍歷 (root - left - right)

    private void preOrder(int index) { // index 為某一節點在 arr 中的位置
        if (arr == null || arr.length == 0) {
            System.out.println("tree is empty!");
            return;
        }
        System.out.println(arr[index]);
        int leftNodeIndex = 2 * index + 1;
        int rightNodeIndex = 2 * index + 2;
        if (leftNodeIndex < arr.length) {
            preOrder(leftNodeIndex); // 左節點遞迴 2n + 1
        }
        if (rightNodeIndex < arr.length) {
            preOrder(rightNodeIndex);
        }
    }

    private void inOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("tree is empty!");
            return;
        }
        int leftNodeIndex = 2 * index + 1;
        if (leftNodeIndex < arr.length) {
            inOrder(leftNodeIndex); // 左節點遞迴 2n + 1
        }

        System.out.println(arr[index]);

        int rightNodeIndex = 2 * index + 2;
        if (rightNodeIndex < arr.length) {
            inOrder(rightNodeIndex);
        }
    }

    private void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("tree is empty!");
            return;
        }
        int leftNodeIndex = 2 * index + 1;
        if (leftNodeIndex < arr.length) {
            postOrder(leftNodeIndex); // 左節點遞迴 2n + 1
        }

        int rightNodeIndex = 2 * index + 2;
        if (rightNodeIndex < arr.length) {
            postOrder(rightNodeIndex);
        }

        System.out.println(arr[index]);
    }


}
