package com.frank.mytest.codetest.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 給定一個長度為“偶數” n 的 LinkedList
 * 每個節點首尾兩兩可組為一個 Twin
 * 若以 index 0 開始算，index i 的 twin 會在 (n - 1 - i) 位置
 * 找出 twin 總和最大為多少？
 */
public class MaximumTwinSumOfALinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode one = new ListNode(4);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(1);

        head.next = one;
        one.next = two;
        two.next = three;

        System.out.println(pairSum(head));
        System.out.println(pairSumV2(head));
    }

    public static int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int l = 0;
        int r = list.size() - 1;
        int max = Integer.MIN_VALUE;

        while (l < r) {
            max = Math.max(max, list.get(l) + list.get(r));
            l++;
            r--;
        }
        return max;
    }

    /**
     * 移動到中心點前，將前一半的 LinkedList 反轉
     * 再依序將兩個 node 相加計算最大值
     */
    public static int pairSumV2(ListNode head) {
        // O(1) Space Complexity
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        int max = Integer.MIN_VALUE;
        ListNode l = prev;
        ListNode r = slow;

        while (l != null && r != null) {
            max = Math.max(max, l.val + r.val);
            l = l.next;
            r = r.next;
        }
        return max;
    }
}
