package com.frank.mytest.codetest.leetcode.linkedlist;

import java.util.*;

public class LFUCache {
    private final int capacity;
    private final Map<Integer, LRU> countToLRUMap; // Count of occurrence -> LRU
    private final Map<Integer, Node> nodeMap;
    private int curMin;

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
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

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.countToLRUMap = new HashMap<>();
        this.nodeMap = new HashMap<>();
        this.curMin = 1;
    }

    private void transfer(Node node) {
        LRU lru = this.countToLRUMap.get(node.count);
        lru.remove(node);
        if (lru.isEmpty()) {
            this.countToLRUMap.remove(node.count);
            if (this.curMin == node.count) {
                this.curMin++;
            }
        }
        node.count++;
        this.countToLRUMap.putIfAbsent(node.count, new LRU());
        this.countToLRUMap.get(node.count).insert(node);
    }

    public int get(int key) {
//        System.out.println(this.nodeMap + " " + curMin);
        if (!this.nodeMap.containsKey(key)) {
//            System.out.printf("key %s is not exist %n", key);
            return -1;
        }
        Node node = this.nodeMap.get(key);
        int val = node.val;
        this.transfer(node);
        return val;
    }

    public void put(int key, int value) {
//        System.out.println(this.nodeMap + " " + curMin);
        Node node;
        if (this.nodeMap.containsKey(key)) {
            node = this.nodeMap.get(key);
            node.val = value;
            this.transfer(node);
        } else {
            if (this.nodeMap.size() == this.capacity) {
//                System.out.printf("put key %s, over cap %n", key);
                LRU lru = this.countToLRUMap.get(this.curMin);
                int removedKey = lru.popLRU();
                nodeMap.remove(removedKey);
                if (lru.isEmpty()) {
                    this.countToLRUMap.remove(this.curMin);
                    this.curMin++;
                }
            }
            node = new Node(key, value);
            this.nodeMap.put(key, node);
            this.countToLRUMap.putIfAbsent(1, new LRU());
            this.countToLRUMap.get(1).insert(node);
            this.curMin = Math.min(this.curMin, 1);
        }
    }

    private class LRU {
        private final Node head;
        private final Node tail;
        private int size;

        public LRU() {
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        public void insert(Node node) {
            Node last = this.tail.prev;
            last.next = node;
            node.prev = last;
            node.next = this.tail;
            this.tail.prev = node;
            this.size++;
        }

        public int remove(Node node) {
            if (this.isEmpty()) return -1;
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            this.size--;
            return node.key;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int popLRU() {
            return this.remove(this.head.next);
        }

    }

    private class Node {
        private int key;
        private int val;
        private int count;
        private Node prev;
        private Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.count = 1;
        }

        @Override
        public String toString() {
            return String.format("val: %s, count: %s", val, count);
        }
    }
}
