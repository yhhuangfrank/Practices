package com.frank.mytest.codetest.dsa.heap;

import java.util.PriorityQueue;

/**
 * 使用兩個 heap 來尋找一個 stream 當中的中位數
 * small 內的數皆為 <= large 內的數
 * 限制兩者大小差異在 1 以內
 */
public class Median {
    private final PriorityQueue<Integer> small;
    private final PriorityQueue<Integer> large;

    public Median() {
        this.small = new PriorityQueue<>(); // max heap
        this.large = new PriorityQueue<>(); // min heap
    }

    // O(logN) time
    public void insert(int val) {
        // push 到 small 中
        this.small.add(-val);
        if (!this.small.isEmpty() && !this.large.isEmpty() &&
                -this.small.peek() > this.large.peek()) {
            // 移動到 large 中
            this.large.add(-this.small.poll()); // 乘以 -1 以實現 maxHeap
        }
        // 檢查兩個 heap 大小差異
        if (this.small.size() - this.large.size() > 1) {
            this.large.add(-this.small.poll());
        } else if (this.large.size() - this.small.size() > 1) {
            this.small.add(-this.large.poll());
        }
    }

    // O(1) time
    public double getMedian() {
        // 奇數個
        if (this.small.size() > this.large.size()) {
            return -this.small.peek();
        } else if (this.large.size() > this.small.size()) {
            return this.large.peek();
        }
        // 偶數個
        if (this.small.isEmpty()) return 0;
        return ((double) -this.small.peek() + (double) this.large.peek()) / 2;
    }

}
