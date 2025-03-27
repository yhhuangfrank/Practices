package com.frank.mytest.codetest.leetcode.linkedlist;

public class ReverseLinkedList2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        Solution solution = new Solution();
        ListNode newHead = solution.reverseBetweenV2(head, 2, 4);
        ListNode cur = newHead;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    static class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode headPortion = dummy;
            ListNode cur = dummy;
            int i = 0;
            while (i < right) {
                if (i + 1 == left) {
                    headPortion = cur;
                }
                i += 1;
                cur = cur.next;
            }
            ListNode tailPortion = cur.next;
            cur.next = null;
            ListNode subListHead = headPortion.next;
            headPortion.next = null;

            headPortion.next = reverseLinkedList(subListHead);
            subListHead.next = tailPortion;
            return dummy.next;
        }
        // one-pass solution
        public ListNode reverseBetweenV2(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(-1, head);
            // 1) reach node at 'left'
            ListNode leftPrev = dummy;
            ListNode cur = head;
            for (int i = 0; i < left - 1; i++) {
                leftPrev = cur;
                cur = cur.next;
            }
            // 2) reverse from left to right
            ListNode prev = null;
            ListNode node = cur;
            for (int i = 0; i < right - left + 1; i++) {
                ListNode temp = node.next;
                node.next = prev;
                prev = node;
                node = temp;
            }
            // 3) update pointers
            leftPrev.next = prev;
            cur.next = node;
            return dummy.next;
        }

        private ListNode reverseLinkedList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }
    }
}
