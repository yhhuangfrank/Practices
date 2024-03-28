package com.frank.mytest.test.dsa.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 8, 7, 9, 12, 6};
        int[] arr = {10, 20, 30, 40, 50, 25};
        AVLTree avlTree = new AVLTree();

        for (int num : arr) {
            avlTree.root = avlTree.addNode(avlTree.root, num);
        }

        avlTree.inOrder(avlTree.root);

        System.out.println("current root is : " + avlTree.root);
    }

    private static class AVLTree {
        private Node root;

        // 從 node 節點上加入新節點，值為 value。返回添加後的新 root 節點
        private Node addNode(Node node, int value) {
            // 1) 依照一般 BST 添加節點
            if (node == null) return (new Node(value));

            if (value < node.value) {
                node.left = addNode(node.left, value);
            } else if (value > node.value) {
                node.right = addNode(node.right, value);
            } else {
                return node; // 不允許有相同值
            }

            // 2) 更新添加 node 的高度
            node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

            // 3) 取得 balance 判斷目前是否 AVLTree 不平衡
            int balance = getBalance(node);

            // 根據 4 種 case 進行左右旋

            // Left-Left Case
            if (balance > 1 && value < node.left.value) {
                return rightRotate(node);
            }

            // Right-Right Case
            if (balance < -1 && value > node.right.value) {
                return leftRotate(node);
            }

            // Left-Right Case
            if (balance > 1 && value > node.left.value) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right-Left Case
            if (balance < -1 && value < node.right.value) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node; // 返回原有未更動的指針
        }

        private int getBalance(Node node) {
            if (node == null) return 0;
            return this.height(node.left) - this.height(node.right);
        }

        public int height(Node node) {
            if (node == null) return 0;
            return node.getHeight();
        }

        // 使用 inOrder 遍歷節點，節點值會是升序排列
        public void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.println(node);
                inOrder(node.right);
            }
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

        // 針對 node 進行右旋，返回新的 root
        public Node rightRotate(Node node) {
            Node temp = node.left;
            Node originalRight = temp.right;

            // 右旋
            temp.right = node;
            node.left = originalRight;

            // update heights
            node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
            temp.height = Math.max(this.height(node.left), this.height(node.right)) + 1;

            return temp;
        }

        // 針對 node 進行左旋，返回新的 root
        public Node leftRotate(Node node) {
            Node temp = node.right;
            Node originalLeft = temp.left;

            // 左旋
            temp.left = node;
            node.right = originalLeft;

            // update heights
            node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
            temp.height = Math.max(this.height(node.left), this.height(node.right)) + 1;

            return temp;
        }
    }

    @Getter
    @Setter
    @ToString
    private static class Node {
        private int value;

        private int height;

        @ToString.Exclude
        private Node left;

        @ToString.Exclude
        private Node right;

        public Node(int value) {
            this.value = value;
            this.height = 1; // 初始為 1
        }

        // 按照遞迴方式添加，需滿足 BST 的要求
//        public void add(Node node) {
//            if (node == null) return;
//
//            if (node.getValue() < this.value) {
//                if (this.left == null) { // 若左子節點為空
//                    this.left = node;
//                    return;
//                }
//                this.left.add(node); // 向左遞迴
//            } else {
//                if (this.right == null) {
//                    this.right = node;
//                    return;
//                }
//                this.right.add(node);
//            }
//
//            // 當添加完成時，若 (右子樹的高度 - 左子樹的高度) > 1，進行左旋
//            if (this.rightSubTreeHeight() - this.leftSubTreeHeight() > 1) {
//                this.leftRotate();
//            }
//
//            // 當添加完成時，若 (左子樹的高度 - 右子樹的高度) > 1，進行右旋
//            if (this.leftSubTreeHeight() > this.rightSubTreeHeight()) {
//                this.rightRotate();
//            }
//        }


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

//        // 返回目前節點的高度 (根節點是以目前節點為基準)
//        public int height() {
//            // 找尋左子樹高度和右子樹高度兩者較高的值
//            int leftSubTreeHeight = (this.left == null ? 0 : this.left.height()); // 有節點的話則遞迴
//            int rightSubTreeHeight = (this.right == null ? 0 : this.right.height());
//            return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1; // 加上目前的 node
//        }
//
//        // 返回左子樹高度
//        public int leftSubTreeHeight() {
//            if (this.left == null) return 0;
//            return this.left.height();
//        }
//
//        // 返回右子樹高度
//        public int rightSubTreeHeight() {
//            if (this.right == null) return 0;
//            return this.right.height();
//        }
//
//        // 左旋
//        public void leftRotate() {
//            // 創建新的節點，以目前根節點的值
//            Node newNode = new Node(value);
//            // 新節點的左子樹設為目前節點的左子樹
//            newNode.left = this.left;
//            // 新節點的右子樹設置目前節點右子樹的左子樹
//            newNode.right = this.right.left;
//            // 把目前節點的值替換為右子節點的值
//            this.value = this.right.value;
//            // 目前節點的右子樹設置成右子樹的右子樹
//            this.right = this.right.right;
//            // 把目前節點的左子樹設置成新節點
//            this.left = newNode;
//        }
//
//        // 右旋
//        public void rightRotate() {
//            // 創建新的節點，以目前根節點的值
//            Node newNode = new Node(value);
//            // 新節點的右子樹設為目前節點的右子樹
//            newNode.right = this.right;
//            // 新節點的左子樹設置目前節點右子樹的左子樹
//            newNode.left = this.left.right;
//            // 把目前節點的值替換為左子節點的值
//            this.value = this.left.value;
//            // 目前節點的左子樹設置成左子樹的左子樹
//            this.left = this.left.left;
//            // 把目前節點的右子樹設置成新節點
//            this.right = newNode;
//        }
    }
}
