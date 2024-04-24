package com.frank.mytest.codetest.dsa.tree;

import lombok.ToString;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 25, 35, 60};
        AVLTree avlTree = new AVLTree();

        for (int num : arr) {
            avlTree.addNode(num);
        }

        avlTree.inOrder();

        System.out.println("current root is : " + avlTree.root);
        avlTree.deleteNode(30);
        System.out.println("==================================");
        avlTree.inOrder();

        System.out.println("current root is : " + avlTree.root);
    }

    private static class AVLTree {
        private Node root;

        public void addNode(int value) {
            this.root = addNode(this.root, value);
        }

        // 從 node 節點上加入新節點，值為 value。返回添加後的新 root 節點
        private Node addNode(Node node, int value) {
            // 1) 依照一般 BST 添加節點
            if (node == null) return new Node(value);

            if (value < node.value) {
                node.left = addNode(node.left, value);
            } else if (value > node.value) {
                node.right = addNode(node.right, value);
            } else {
                return node; // 不允許有相同值
            }
            // 更新添加 node 的高度
            node.height = 1 + Math.max(this.height(node.left), this.height(node.right));
            return balanceSubTree(node, value);
        }

        /**
         *
         * @param node 目前節點
         * @param value 欲新增的節點值
         * @return 返回子樹平衡後的根節點
         */
        private Node balanceSubTree(Node node, int value) {
            if (node == null) return null;
            System.out.println("balanceSubTree: " + node);
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

            return node;
        }

        private int getBalance(Node node) {
            if (node == null) return 0;
            // 左子樹高度 - 右子樹高度
            return this.height(node.left) - this.height(node.right);
        }

        private int height(Node node) {
            if (node == null) return 0;
            return node.height;
        }

        // 使用 inOrder 遍歷節點，節點值會是升序排列
        public void inOrder() {
            inOrder(this.root);
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.println(node);
                inOrder(node.right);
            }
        }

        public void deleteNode(int value) {
            this.root = deleteNode(this.root, value);
        }

        /**
         * 在任意大小的 BST root 上往子樹不斷遞迴縮小範圍找尋對應節點
         * 刪除該節點後，返回該子樹上新的 root
         *
         * @param node  任意 BST 的 節點
         * @param value 欲刪除的節點值
         * @return 返回新的 root
         */
        private Node deleteNode(Node node, int value) {
            if (node == null) return null;

            // 檢查是否為要刪除的節點，若不是則遞迴往子樹找尋
            if (value > node.value) {
                node.right = deleteNode(node.right, value);
                return node;
            } else if (value < node.value) {
                node.left = deleteNode(node.left, value);
                return node;
            }

            // 抵達此處代表此時 root 為欲刪除的節點，區分情況

            // 1) root 有一棵子樹
            if (node.left == null) {
                return node.right; // 返回右子樹給父節點接上
            } else if (node.right == null) {
                return node.left;
            } else { // 2) root 有兩棵子樹
                Node minNode = findMinNode(node.right);
                node.value = minNode.value;
                node.right = deleteNode(node.right, minNode.value);
            }
            // 更新添加 node 的高度
            node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

            int balance = getBalance(node);

            // 根據 4 種 case 進行左右旋

            // Left-Left Case
            if (balance > 1 && getBalance(node.left) >= 0) {
                return rightRotate(node);
            }

            // Right-Right Case
            if (balance < -1 && getBalance(node.right) <= 0) {
                return leftRotate(node);
            }

            // Left-Right Case
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right-Left Case
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        private Node findMinNode(Node root) {
            Node temp = root;
            while (temp != null && temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }

        // 針對 node 進行右旋，返回新的 root
        private Node rightRotate(Node node) {
            Node temp = node.left;
            Node originalRight = temp.right;

            // 右旋
            temp.right = node;
            node.left = originalRight;

            // update heights
            node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
            temp.height = Math.max(this.height(temp.left), this.height(temp.right)) + 1;

            return temp;
        }

        // 針對 node 進行左旋，返回新的 root
        private Node leftRotate(Node node) {
            Node temp = node.right;
            Node originalLeft = temp.left;

            // 左旋
            temp.left = node;
            node.right = originalLeft;

            // update heights
            node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
            temp.height = Math.max(this.height(temp.left), this.height(temp.right)) + 1;

            return temp;
        }
    }

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
    }
}
