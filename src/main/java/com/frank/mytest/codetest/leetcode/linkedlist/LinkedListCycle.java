package com.frank.mytest.codetest.leetcode.linkedlist;

/**
 *
 * 判斷一 Linked List 中是否有構成還
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = two;
        System.out.println(hasCycleV2(head));
        // 1 -> 2 -> 3 -> 4
        //       ^______ /
    }

    /**
     * 使用 two pointer，一個走兩步，一個走一步
     * 若有環狀，走一步的最終會交會到走一步的
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode oneStep = head;
        ListNode twoStep = head;
        do {
            if (twoStep.next == null || twoStep.next.next == null) {
                return false;
            }
            twoStep = twoStep.next.next;
            oneStep = oneStep.next;
        } while (oneStep != twoStep);

        return true;
    }

    // 用一般 while
    public static boolean hasCycleV2(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }

        return false;
    }
}
