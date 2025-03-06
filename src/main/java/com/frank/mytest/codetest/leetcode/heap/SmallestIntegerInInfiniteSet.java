package com.frank.mytest.codetest.leetcode.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestIntegerInInfiniteSet {

    public static void main(String[] args) {
        SmallestInfiniteSet obj = new SmallestInfiniteSet();
        obj.addBack(1);
        System.out.println(obj.popSmallest());
        System.out.println(obj.popSmallest());
        System.out.println(obj.popSmallest());
        obj.addBack(1);
        System.out.println(obj.popSmallest());
        System.out.println(obj.popSmallest());
        System.out.println(obj.popSmallest());
    }

//    Input
//["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
//        [[], [2], [], [], [], [1], [], [], []]
//    Output
//[null, null, 1, 2, 3, null, 1, 4, 5]

    static class SmallestInfiniteSet {
        private PriorityQueue<Integer> minHeap;
        private Set<Integer> nums; // nums for adding back

        public SmallestInfiniteSet() {
            this.minHeap = new PriorityQueue<>();
            this.nums = new HashSet<>();
        }

        public int popSmallest() {
            int peek = this.minHeap.peek();
            if (this.minHeap.size() == 1) {
                this.minHeap.add(peek + 1);
            }
            this.nums.add(peek);
            return this.minHeap.poll();
        }

        public void addBack(int num) {
            if (this.nums.contains(num)) {
                this.nums.remove(num);
                this.minHeap.add(num);
            }
        }
    }


}
