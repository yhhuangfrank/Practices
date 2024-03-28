package com.frank.mytest.test.leetcode.linkedlist;


import java.util.ArrayList;
import java.util.List;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
//        ListNode three = new ListNode(3);

        head.next = two;
        two.next = one;
//        one.next = three;

        ListNode sortedListHead = sortListV2(head);
        System.out.println(sortedListHead);
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        List<ListNode> nodes = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodes.add(temp);
            temp = temp.next;
        }

        List<ListNode> sortedList = mergeSort(nodes);
        ListNode curr = null;
        ListNode next = null;
        for (int i = 0; i < sortedList.size(); i += 1) {
            curr = sortedList.get(i);
            if(i + 1 < sortedList.size()) {
                next = sortedList.get(i+1);
            } else{
                next = null;
            }
            curr.next = next;
        }
        return sortedList.get(0);
    }

    public static List<ListNode> mergeSort(List<ListNode> nodes) {
        if (nodes.size() == 1) return nodes;

        int middle = (nodes.size() - 1) >> 1;
        List<ListNode> l = new ArrayList<>();
        List<ListNode> r = new ArrayList<>();

        int index = 0;
        ListNode temp;
        while (index <= middle) {
            temp = nodes.get(index);
            l.add(temp);
            index++;
        }
        while (index < nodes.size()) {
            temp = nodes.get(index);
            r.add(temp);
            index++;
        }

        return merge(mergeSort(l), mergeSort(r));
    }

    public static List<ListNode> merge(List<ListNode> l, List<ListNode> r) {
        int i = 0;
        int j = 0;

        List<ListNode> nodes = new ArrayList<>();

        while (i < l.size() && j < r.size()) {
            if (l.get(i).val <= r.get(j).val) {
                nodes.add(l.get(i));
                i++;
            } else {
                nodes.add(r.get(j));
                j++;
            }
        }

        while (i < l.size()) {
            nodes.add(l.get(i));
            i++;
        }

        while (j < r.size()) {
            nodes.add(r.get(j));
            j++;
        }

        return nodes;
    }

    public static ListNode sortListV2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head; // 每次走一個節點
        ListNode prev = slow; // 記住 slow 前一個節點，用來斷開連結用
        ListNode fast = head; // 每次走兩個節點

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此時 fast 為最後的節點或 null, slow 走到中點
        prev.next = null; // 切分兩個部分，另一條 LinkedList 由 slow 作為開頭
        return merge(sortListV2(head), sortListV2(slow));
    }

    public static ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(); // 用於串接 node1, node2 上的節點
        ListNode temp = dummy;
        ListNode l = node1;
        ListNode r = node2;

        while (l != null && r != null) {
            if (l.val <= r.val) {
                temp.next = l;
                l = l.next;
            } else {
                temp.next = r;
                r = r.next;
            }
            temp = temp.next;
        }
        // 將未遍歷完的節點接上
        if (l != null) {
            temp.next = l;
        } else if (r != null) {
            temp.next = r;
        }

        return dummy.next;
    }
}
