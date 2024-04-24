package com.frank.mytest.codetest.dsa.tree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        Node root = new Node(1, "Frank");
        Node jack = new Node(2, "Jack");
        Node wendy = new Node(3, "Wendy");
        Node amy = new Node(4, "Amy");
        Node tom = new Node(5, "Tom");

        root.setLeft(jack);
        root.setRight(wendy);
        wendy.setRight(amy);
        wendy.setLeft(tom);

        tree.setRoot(root);
        // 遍歷
        System.out.println("===前序遍歷===");
        tree.preOrder();
//        System.out.println("===中序遍歷===");
//        tree.inOrder();
//        System.out.println("===後序遍歷===");
//        tree.postOrder();

        // 查找
//        System.out.println("==========================");
//        System.out.println("前序查找： " + tree.preOrderSearch(5));
//        System.out.println("中序查找： " + tree.inOrderSearch(5));
//        System.out.println("後序查找： " + tree.postOrderSearch(5));

        // 刪除
//        tree.removeNode(5);
        tree.removeNode(3);
        System.out.println("===前序遍歷===");
        tree.preOrder();

    }
}

/**
 * 定義樹
 */
@Getter
@Setter
@NoArgsConstructor
class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root == null) {
            System.out.println("tree is empty!");
            return;
        }
        this.root.preOrder();
    }

    public void inOrder() {
        if (this.root == null) {
            System.out.println("tree is empty!");
            return;
        }
        this.root.inOrder();
    }

    public void postOrder() {
        if (this.root == null) {
            System.out.println("tree is empty!");
            return;
        }
        this.root.postOrder();
    }

    // pre-order 查找
    public Node preOrderSearch(int no) {
        if (this.root == null) return null;
        return this.root.preOrderSearch(no);
    }

    // in-order 查找
    public Node inOrderSearch(int no) {
        if (this.root == null) return null;
        return this.root.inOrderSearch(no);
    }

    // post-order 查找
    public Node postOrderSearch(int no) {
        if (this.root == null) return null;
        return this.root.postOrderSearch(no);
    }

    // 刪除節點
    public void removeNode(int no) {
        if (this.root == null) {
            System.out.println("tree is empty!");
        } else if (this.root.getNo() == no) {
            this.root = null;
        } else {
            this.root.remove(no);
        }
    }

}

/**
 * 定義節點
 */
@Getter
@Setter
@ToString
class Node {
    private int no;

    private String name;

    @ToString.Exclude
    private Node left;

    @ToString.Exclude
    private Node right;

    public Node(int no, String name) {
        this.no= no;
        this.name = name;
    }

    // pre-Order method
    public void preOrder() {
        System.out.println("目前節點：" + this);
        // 遞迴左子樹
        if (this.left != null) {
            this.left.preOrder();
        }
        // 遞迴右子樹
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // in-order traverse
    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println("目前節點：" + this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    // post-order traverse
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println("目前節點：" + this);
    }

    // pre-order 查找 node
    public Node preOrderSearch(int no) {
        System.out.println("進入 preOrderSearch");
        // 先比較目前節點是否為欲找尋的節點
        if (this.no == no) return this;

        Node result = null;

        // 向左子樹遞迴查找
        if (this.left != null) {
            result = this.left.preOrderSearch(no); // 取得遞迴結果
        }
        if (result != null) return result;

        // 左子樹沒有找到，向右子樹遞迴查找
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }

        return result; // 無論右子樹是否有找到都返回結果
    }

    // in-order 查找 node
    public Node inOrderSearch(int no) {
        Node result = null;

        // 向左子樹遞迴查找
        if (this.left != null) {
            result = this.left.inOrderSearch(no); // 取得遞迴結果
        }
        if (result != null) return result;

        // 比較目前節點是否為欲找尋的節點
        System.out.println("進入 inOrderSearch");
        if (this.no == no) return this;

        // 左子樹沒有找到和父節點都不是，向右子樹遞迴查找
        if (this.right != null) {
            result = this.right.inOrderSearch(no);
        }

        return result;
    }

    // post-order 查找 node
    public Node postOrderSearch(int no) {
        Node result = null;

        // 向左子樹遞迴查找
        if (this.left != null) {
            result = this.left.postOrderSearch(no); // 取得遞迴結果
        }
        if (result != null) return result;


        // 左子樹沒有找到，向右子樹遞迴查找
        if (this.right != null) {
            result = this.right.postOrderSearch(no);
        }
        if (result != null) return result;

        // 比較目前節點是否為欲找尋的節點
        System.out.println("進入 postOrderSearch");
        if (this.no == no) return this;

        return null;
    }

    // 刪除節點，此處作法先不考慮是否要把子節點向上提升一層的問題
    // 1. 如果刪除的節點是葉子節點，則刪除該節點
    // 2. 如果刪除的節點是非葉子節點，則刪除該子樹

    /**
     * 思路：
     * 1. 因二叉樹為單向的，所以要判斷目前節點的子節點是否為需要刪除的節點，而不能用目前節點來判斷
     * 2. 如果目前節點的左子節點不為空，並且左子節點就是要刪除的節點，就使用 this.left = null 並返回結束遞迴
     * 3. 若左子節點不是，則同樣方法判斷右子節點
     * 4. 若左右子節點都沒有刪除，則向左子樹遞迴
     * 5. 若第 4 步也沒有刪除節點，則向右子樹遞回
     *
     */
    public void remove(int no) {
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.remove(no);
        }
        if (this.right != null) {
            this.right.remove(no);
        }
    }
}
