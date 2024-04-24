package com.frank.mytest.codetest.leetcode.linkedlist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListNode {

    @ToString.Exclude
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }

}
