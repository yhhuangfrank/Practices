package com.frank.mytest.codetest.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LFUCache2 {
    private final int capacity;
    private final Map<Integer, LRU> countToLRUMap; // Count of occurrence -> LRU
    private final Map<Integer, Integer> countMap;
    private final Map<Integer, Integer> valMap;
    private int lrfCount;

    public static void main(String[] args) {
        LFUCache2 lfu = new LFUCache2(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));      // return 1, cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2. cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3, cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1. cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3, cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));      // return 4, cache=[4,3], cnt(4)=2, cnt(3)=3
    }

    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.countToLRUMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.valMap = new HashMap<>();
        this.lrfCount = 0;
    }

    private void transfer(int key) {
        int count = this.countMap.get(key);
        this.countMap.put(key, count + 1);
        this.countToLRUMap.putIfAbsent(count, new LRU());
        this.countToLRUMap.get(count).remove(key);

        this.countToLRUMap.putIfAbsent(count + 1, new LRU());
        this.countToLRUMap.get(count + 1).insert(key);

        if (count == this.lrfCount && countToLRUMap.get(count).size == 0) {
            lrfCount++;
        }
    }

    public int get(int key) {
        if (!this.valMap.containsKey(key)) {
            return -1;
        }
        this.transfer(key);
        return this.valMap.get(key);
    }

    public void put(int key, int value) {
        if (!this.valMap.containsKey(key) && this.valMap.size() == this.capacity) {
            int removeKey = this.countToLRUMap.get(this.lrfCount).popLRU();
            this.valMap.remove(removeKey);
            this.countMap.remove(removeKey);
        }
        this.valMap.put(key, value);
        this.countMap.putIfAbsent(key, 0);
        this.transfer(key);
        this.lrfCount = Math.min(this.lrfCount, this.countMap.get(key));
    }

    private class LRU {
        private final Node head;
        private final Node tail;
        private int size;
        private final Map<Integer, Node> nodeMap;

        public LRU() {
            this.head = new Node(-1);
            this.tail = new Node(-1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
            this.nodeMap = new HashMap<>();
        }

        public void insert(int val) {
            Node node = new Node(val);
            Node last = this.tail.prev;
            last.next = node;
            node.prev = last;
            node.next = this.tail;
            this.tail.prev = node;
            this.size++;
            this.nodeMap.put(val, node);
        }

        public void remove(int val) {
            if (this.isEmpty()) return;
            if (this.nodeMap.containsKey(val)) {
                Node node = this.nodeMap.get(val);
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                next.prev = prev;
                this.size--;
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int popLRU() {
            int val = this.head.next.val;
            this.remove(val);
            return val;
        }
    }

    private class Node {
        private int val;
        private Node prev;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("val: %s", val);
        }
    }
}
