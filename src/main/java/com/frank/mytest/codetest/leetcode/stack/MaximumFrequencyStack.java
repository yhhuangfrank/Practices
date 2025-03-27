package com.frank.mytest.codetest.leetcode.stack;

import java.util.*;

public class MaximumFrequencyStack {

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println(freqStack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        System.out.println(freqStack.pop());   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        System.out.println(freqStack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        System.out.println(freqStack.pop());   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7]
        System.out.println("===================");
        FreqStack2 freqStack2 = new FreqStack2();
        freqStack2.push(5); // The stack is [5]
        freqStack2.push(7); // The stack is [5,7]
        freqStack2.push(5); // The stack is [5,7,5]
        freqStack2.push(7); // The stack is [5,7,5,7]
        freqStack2.push(4); // The stack is [5,7,5,7,4]
        freqStack2.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println(freqStack2.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        System.out.println(freqStack2.pop());   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        System.out.println(freqStack2.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        System.out.println(freqStack2.pop());   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7]
    }

    // solution 1 - heap, O(logN) time
    static class FreqStack {
        private Map<Integer, Integer> freqMap;
        private PriorityQueue<int[]> heap; // triplet of [freq, idx, value]
        private int idx;

        public FreqStack() {
            this.freqMap = new HashMap<>();
            this.heap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(b[0], a[0]));
            this.idx = 0;
        }

        public void push(int val) {
            this.freqMap.put(val, this.freqMap.getOrDefault(val, 0) + 1);
            this.heap.add(new int[]{this.freqMap.get(val), this.idx++, val});
        }

        public int pop() {
            int num = this.heap.poll()[2];
            this.freqMap.put(num, this.freqMap.get(num) - 1);
            return num;
        }
    }

    // solution 2 - stack, O(1) time
    static class FreqStack2 {
        private Map<Integer, Integer> freq;
        private Map<Integer, Deque<Integer>> countMap; // map to nums with the same count
        private int curMaxCount;

        public FreqStack2() {
            this.freq = new HashMap<>();
            this.countMap = new HashMap<>();
            this.curMaxCount = 0;
        }

        public void push(int val) {
            this.freq.put(val, this.freq.getOrDefault(val, 0) + 1);
            int count = this.freq.get(val);
            if (count > this.curMaxCount) {
                this.curMaxCount = count;
                this.countMap.put(count, new ArrayDeque<>());
            }
            this.countMap.get(count).addLast(val);
        }

        public int pop() {
            int num = this.countMap.get(this.curMaxCount).pollLast();
            this.freq.put(num, this.freq.get(num) - 1);
            if (this.countMap.get(this.curMaxCount).isEmpty()) {
                this.curMaxCount--;
            }
            return num;
        }
    }
}
