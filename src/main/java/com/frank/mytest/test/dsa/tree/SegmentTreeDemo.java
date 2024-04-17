package com.frank.mytest.test.dsa.tree;

public class SegmentTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 7, 1, 4, 2};
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.sum);
        System.out.println(segmentTree.query(1,4));
        segmentTree.update(3, 6);
        System.out.println(segmentTree.sum);
        System.out.println(segmentTree.query(1,4));
    }
}
