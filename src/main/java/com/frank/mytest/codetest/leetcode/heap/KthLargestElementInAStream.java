package com.frank.mytest.codetest.leetcode.heap;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {

    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[] {4,5,8,2});
        System.out.println(obj.add(3)); // 4
        System.out.println(obj.add(5)); // 5
        System.out.println(obj.add(10)); // 5
        System.out.println(obj.add(9)); // 8
        System.out.println(obj.add(4)); // 8
    }

    private static class KthLargest {
        private int size;
        private PriorityQueue<Integer> minHeap;

        public KthLargest(int k, int[] nums) {
            this.size = k;
            this.minHeap = new PriorityQueue<>();
            for (int n : nums) {
                this.minHeap.add(n);
            }
            while (this.minHeap.size() > k) {
                this.minHeap.poll();
            }
        }

        public int add(int val) {
            if (this.minHeap.size() < this.size) {
                this.minHeap.add(val);
            } else if (this.minHeap.peek() < val) {
                this.minHeap.poll();
                this.minHeap.add(val);
            }
            return this.minHeap.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}

