package com.frank.mytest.test.leetcode.linkedlist;


public class SwapNodesInPair {
    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = four;

        System.out.println(swapPairs(head));
        ListNode currNode = head;
        while (currNode != null) {
            System.out.println(currNode);
            currNode = currNode.next;
        }
    }

    // 給定一個 LinkedList, 每兩個節點彼此交換位置，最終返回新 list 的 head
    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode temp = head;
        if (head.next != null) {
            temp = head.next;
            helper(head, null);
        }
        return temp;
    }

    public static void helper(ListNode curr, ListNode prev) {
        if (curr == null) return;
        ListNode tmp;
        if (curr.next != null) {
            tmp = curr.next;
            curr.next = tmp.next;
            tmp.next = curr;
            if (prev != null) {
                prev.next = tmp;
            }
            helper(curr.next, curr);
        }
    }
}
