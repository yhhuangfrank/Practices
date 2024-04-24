package com.frank.mytest.codetest.dsa.linkedList;

import lombok.Data;

/**
 * 約瑟夫問題
 * 編號為 1,2 ... n 的人坐一圈，約定編號為 k (1 <= k <= n) 的人
 * 從 1 開始報數，數到 m 的那位出列，再由後面一位繼續數，數到 m 的出列
 * 依此類推，問出隊的編號順序為？
 */
public class Josepfu {

    public static void main(String[] args) {
        // 假設 n = 5, k = 1, m = 2 (共 5 人，從 1 號開始報數，數到 2 的人出列)
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addNode(5);
//        list.traverse();
        list.count(1, 2, 5);
    }
}

/**
 * 環狀單向 LinkedList
 */
class CircleSingleLinkedList {
    private Node first; // 固定指向第一個節點

    public void addNode(int num) {
        if (num < 1) return;

        Node currentNode = null;
        for (int i = 1; i <= num ; i++) {
            Node newNode = new Node(i);
            if (i == 1) {
                first = newNode;
                currentNode = first; // 輔助變量
                newNode.setNext(newNode); // 第一個 Node 的 next 指向自己，構成環
            } else {
                currentNode.setNext(newNode);
                newNode.setNext(first);
                currentNode = newNode; // 移動輔助變量
            }
        }
    }

    // 遍歷 list
    public void traverse() {
        if (first == null) return;

        Node currentNode = first;
        while (true) {
            System.out.println("目前節點的編號為： " + currentNode.getNo());
            if (currentNode.getNext() == first) {
                System.out.println("遍歷完畢");
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * 出隊順序
     * startNo : 從第幾個開始
     * count : 數幾下
     * 創建一個輔助指針 helper 指向環形鏈表的最後一個
     * 移動到起始位置：first 與 helper 往後移動 (startNo - 1) 次
     * 報數：first 與 helper 往後移動 (count - 1) 次
     * 此時 first 指向的節點即為需出隊的節點，出隊方式
     *  first = first.next
     *  helper.next = first
     */
    public void count(int startNo, int count, int listSize) {
        // 簡單校驗
        if (first == null || startNo < 1 || startNo > listSize || count < 1) return;

        Node helper = first; // 輔助變量，指向最後一個節點
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        // 移動到起始位置
        for (int i = 0; i < startNo - 1 ; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 持續報數出隊，直到剩下一人
        while (helper != first) {
            // 報數
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 此時 first 指向需出隊的節點，將其從 list 移除
            System.out.println("出隊編號為： " + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("(最後一個節點)出隊編號為： " + first.getNo()); // 最後留在隊中的節點
    }

}

/**
 * list 內節點
 */
@Data
class Node {
    private int no; // 節點編號
    private Node next;

    public Node (int no) {
        this.no = no;
    }
}
