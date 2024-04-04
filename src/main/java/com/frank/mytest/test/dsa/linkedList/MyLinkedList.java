package com.frank.mytest.test.dsa.linkedList;


import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList {

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();
        list.insertHead(1);
        list.insertHead(2);
        list.insertTail(3);
        list.insertTail(4);
        list.insertHead(5);
        System.out.println(list.remove(2));
        System.out.println(list.remove(0));
        list.insertHead(6);
        list.insertTail(7);
        System.out.println(list.getValues());
    }

    private ListNode head;
    private ListNode tail;

    public MyLinkedList() {
        this.head = new ListNode(-1); // dummy node，方便處理一些 edge cases (head 為 null)
        this.tail = head;
    }

    public int get(int index) {
        int i = 0;
        ListNode temp = this.head.next;

        while(temp != null) {
            if (i == index) {
                return temp.val;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    public void insertHead(int val) {
        ListNode node = new ListNode(val, this.head.next);
        this.head.next = node;
        if (node.next == null) { //  只有一個 node
            this.tail = node;
        }
    }

    public void insertTail(int val) {
        ListNode node = new ListNode(val, null);
        System.out.println(this.tail + ", " + val);
        this.tail.next = node;
        this.tail = node;
    }

    public boolean remove(int index) {
        int i = 0;
        ListNode temp = this.head.next;
        ListNode prev = this.head;

        while(temp != null) {
            if (index == i) {
                prev.next = temp.next;
                if (prev.next == null) {
                    this.tail = prev;
                }
                return true;
            }
            prev = temp;
            temp = temp.next;
            i++;
        }
        return false;
    }

    public List<Integer> getValues() {
        List<Integer> res = new ArrayList<>();
        ListNode temp = this.head.next;
        while (temp != null) {
            res.add(temp.val);
            temp = temp.next;
        }
        return res;
    }

    @ToString
    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        public ListNode(int val) {
            this.val = val;
        }
    }
}
