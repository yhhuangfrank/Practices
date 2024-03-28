package com.frank.mytest.test.dsa.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class BinarySearchTreeDemo {
    /**
     *      50
     *    /    \
     *   30     70
     *  / \     / \
     * 20 40   60 80
     *  \
     *  25
     */
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        int[] arr = {50, 30, 20, 40, 70, 60, 80, 25};
        BST bst = new BST();
        for (int num : arr) {
            bst.addNode(new Node(num));
        }
        bst.inOrder();

        // 刪除葉子節點 2
        bst.deleteNode(bst.root,40);
        System.out.println("=======刪除沒有 child 的節點======");
        bst.inOrder();
        bst.deleteNode(bst.root,70);
        System.out.println("=======刪除有一個 child 的節點======");
        bst.inOrder();
        bst.deleteNode(bst.root,50);
        System.out.println("=======刪除有兩個 child 的節點======");
        bst.inOrder();
        System.out.println("=============");
        System.out.println("root is " + bst.root);
    }

    // 二元排序樹
    private static class BST {
        private Node root;

        private void addNode(Node node) {
            if (this.root == null) {
                this.root = node;
                return;
            }
            this.root.add(node);
        }

        // 使用 inOrder 遍歷節點，節點值會是升序排列
        public void inOrder() {
            if (this.root == null) {
                System.out.println("BST is empty!");
                return;
            }
            this.root.inOrder();
        }

        public Node search(int value) {
            if (this.root == null) return null;
            return this.root.search(value);
        }

        public Node searchParent(int value) {
            if (this.root == null) return null;
            return this.root.searchParent(value);
        }


        /**
         * 在任意大小的 BST root 上往子樹不斷遞迴縮小範圍找尋對應節點
         * 刪除該節點後，返回該子樹上新的 root
         *
         * @param root  任意 BST 的 節點
         * @param value 欲刪除的節點值
         * @return 返回新的 root
         */
        public Node deleteNode(Node root, int value) {
            if (root == null) return null;

            // 檢查是否為要刪除的節點，若不是則遞迴往子樹找尋
            if (value > root.getValue()) {
                root.right = deleteNode(root.right, value);
                return root;
            } else if (value < root.getValue()) {
                root.left = deleteNode(root.left, value);
                return root;
            }

            // 抵達此處代表此時 root 為欲刪除的節點，區分情況

            // 1) root 有一棵子樹
            if (root.left == null) {
                return root.right; // 返回右子樹給父節點接上
            } else if (root.right == null) {
                return root.left;
            } else { // 2) root 有兩棵子樹
                Node succParent = root; // 繼任者的父節點，暫定為 root
                Node successor = root.right; // 繼任者

                // 需找到繼任者，在右子樹一直往左節點找
                while (successor.left != null) {
                    succParent = successor;
                    successor = successor.left;
                }

                // 若找到繼任者，將 successor 右子樹接上父節點左方
                if (succParent != root) {
                    succParent.left = successor.right;
                } else {
                    // 沒有繼任者，代表 successor 沒有左節點，將 successor 右子樹接上父節點右方
                    succParent.right = successor.right;
                }

                // 複製繼任者的值到 root
                root.setValue(successor.getValue());
                return root;
            }
        }
    }

    @Getter
    @Setter
    @ToString
    private static class Node {
        private int value;

        @ToString.Exclude
        private Node left;

        @ToString.Exclude
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        // 按照遞迴方式添加，需滿足 BST 的要求
        public void add(Node node) {
            if (node == null) return;

            if (node.getValue() < this.value) {
                if (this.left == null) { // 若左子節點為空
                    this.left = node;
                    return;
                }
                this.left.add(node); // 向左遞迴
            } else {
                if (this.right == null) {
                    this.right = node;
                    return;
                }
                this.right.add(node);
            }
        }

        // in-order traverse
        public void inOrder() {
            if (this.left != null) {
                this.left.inOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.inOrder();
            }
        }

        // 根據 value 找到對應的節點並返回
        public Node search(int value) {
            if (this.value == value) {
                return this;
            } else if (value < this.value) {
                if (this.left == null) return null;
                return this.left.search(value);
            } else {
                if (this.right == null) return null;
                return this.right.search(value);
            }
        }

        // 根據 value 找到對應的節點的父節點並返回
        public Node searchParent(int value) {
            boolean isTargetFound = (this.left != null && this.left.value == value)  // 左子節點為目標節點
                    || (this.right != null && this.right.value == value); // 右子節點為目標節點
            if (isTargetFound) return this; // 目前節點的子節點為目標節點
            if (this.left != null && value < this.value) {
                return this.left.searchParent(value); // 向左遞迴
            } else if (this.right != null && value >= this.value) {
                return this.right.searchParent(value); // 向右遞迴
            }
            return null;
        }
    }
}
