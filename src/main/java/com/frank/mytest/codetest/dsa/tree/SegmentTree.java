package com.frank.mytest.codetest.dsa.tree;

import lombok.ToString;

@ToString
public class SegmentTree {
     int sum;
     int lowerBound;
     int upperBound;
     SegmentTree left;
     SegmentTree right;

    public SegmentTree(int[] nums) {
        SegmentTree segmentTree = build(nums, 0, nums.length - 1);
        this.sum = segmentTree.sum;
        this.lowerBound = segmentTree.lowerBound;
        this.upperBound = segmentTree.upperBound;
        this.left = segmentTree.left;
        this.right = segmentTree.right;
    }

    public SegmentTree(int sum, int lowerBound, int upperBound) {
        this.sum = sum;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * 使用遞迴構建
     * @param nums 原始 array
     * @param l 下限
     * @param r 上限
     * @return 返回 Segment Tree 根節點
     */
    public SegmentTree build(int[] nums, int l, int r) {
        if (l == r) {
            return new SegmentTree(nums[l], l, r); // 上下限相同，表示只有一個元素
        }
        int m = l + (r - l) / 2;
        SegmentTree root = new SegmentTree(0, l, r);
        root.left = build(nums, l, m);
        root.right = build(nums, m + 1 , r);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    public void update(int index, int val) {
        if (this.lowerBound == this.upperBound && this.lowerBound == index) {
            this.sum = val;
            return;
        }
        int m = (this.lowerBound + this.upperBound) / 2;
        if (index > m) {
            this.right.update(index, val);
        } else {
            this.left.update(index, val);
        }
        this.sum = this.left.sum + this.right.sum;
    }

    public int query(int l, int r) {
        if (this.lowerBound == l && this.upperBound == r) {
            return this.sum;
        }
        int m = (this.lowerBound + this.upperBound) / 2;
        if (l > m) {
            return this.right.query(l, r);
        } else if (r <= m) {
            return this.left.query(l, r);
        } else {
            // 朝兩個方向找尋
            return this.left.query(l, m) + this.right.query(m + 1, r);
        }
    }

}
