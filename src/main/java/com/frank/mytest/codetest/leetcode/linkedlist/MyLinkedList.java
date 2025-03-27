package com.frank.mytest.codetest.leetcode.linkedlist;

public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        System.out.println(myLinkedList.get(1));              // return 2
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        System.out.println(myLinkedList.get(1));              // return 3
    }

    public MyLinkedList() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    public int get(int index) {
        if (index < 0 || index > this.size - 1) return -1;
        Node cur = this.head;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        Node first = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        node.next = first;
        first.prev = node;
        this.size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        Node last = this.tail.prev;
        node.prev = last;
        last.next = node;
        this.tail.prev = node;
        node.next = this.tail;
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) return;
        if (index == this.size) {
            this.addAtTail(val);
            return;
        }
        if (index == 0) {
            this.addAtHead(val);
            return;
        }
        Node cur = this.head;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        Node prev = cur.prev;
        Node node = new Node(val);
        node.prev = prev;
        prev.next = node;
        node.next = cur;
        cur.prev = node;
        this.size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > this.size - 1) return;
        Node cur = this.head;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        System.out.println(cur.prev + ", " + cur.next);
        Node prev = cur.prev;
        Node next = cur.next;
        prev.next = next;
        next.prev = prev;
        this.size--;
    }

    private class Node {
        private int val;
        private Node prev;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
