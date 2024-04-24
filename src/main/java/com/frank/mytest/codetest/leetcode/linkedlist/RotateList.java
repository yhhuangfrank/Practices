package com.frank.mytest.codetest.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 給定一個 LinkedList 的 head 和右移單位 k (k >= 0)
 * 將 list 右移 k 單位後並回傳新 head 節點
 */
public class RotateList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        // 1 -> 2 -> 3 -> 4 -> 5

        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode newHead = rotateRightV3(head, 2);
        System.out.println(newHead); // 4 -> 5 -> 1 -> 2 -> 3
        ListNode temp = newHead;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    // 直觀作法，找到最終值並填回 linkedList
    public static ListNode rotateRightV1(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        List<Integer> list = new ArrayList<>(); // 紀錄原先每個節點值
        ListNode node = head;

        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        // 判斷 k
        int shift = k;
        if (k > list.size()) {
            shift = k % list.size();
        }

        int[] shifted = new int[list.size()]; // 位移後的新節點值
        for (int i = 0; i < list.size(); i++) {
            int shiftIndex = i + shift > list.size() - 1 ? i + shift - list.size() : i + shift;
            shifted[shiftIndex] = list.get(i);
        }
        // 替換每個 LinkedList 每個節點值
        int j = 0;
        node = head;
        while (node != null) {
            node.val = shifted[j];
            node = node.next;
            j++;
        }
        return head;
    }

    public static ListNode rotateRightV2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        // get linked list size
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // validate k
        int shift = k;
        if (k >= size) {
            shift = k % size;
        }
        if (shift == 0) return head;

        int shiftStepsToNewTail = size - shift - 1;
        ListNode newTail = head;
        while (shiftStepsToNewTail > 0) {
            newTail = newTail.next;
            shiftStepsToNewTail--;
        }
        ListNode newHead = newTail.next;
        newTail.next = null; // 切開

        temp = newHead; // 遍歷另一端的尾，接上原有的 head
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        return newHead;
    }

    // V2 的改良
    public static ListNode rotateRightV3(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        // get linked list size
        int size = 1;
        ListNode temp = head;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        // validate k
        int shift = k % size;
        if (shift == 0) return head;

        temp.next = head; // 接成環狀

        int shiftStepsToNewTail = size - shift - 1;
        ListNode newTail = head;
        while (shiftStepsToNewTail > 0) {
            newTail = newTail.next;
            shiftStepsToNewTail--;
        }
        ListNode newHead = newTail.next;
        newTail.next = null; // 切開

        return newHead;
    }
}
