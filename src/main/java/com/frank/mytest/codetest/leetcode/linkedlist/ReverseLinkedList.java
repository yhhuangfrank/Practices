package com.frank.mytest.codetest.leetcode.linkedlist;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = four;
        System.out.println(reverseLinkedList(head));
    }

    public static ListNode reverseLinkedList(ListNode head) {
        ListNode prevNode = null;
        ListNode currNode = head;
        ListNode tmp;
        while (currNode != null) {
            tmp = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = tmp;
        }
        return prevNode;
    }
}
