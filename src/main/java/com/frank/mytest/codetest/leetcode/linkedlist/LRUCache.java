package com.frank.mytest.codetest.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Node head;
    private Node tail;
    private Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.tail.prev = this.head;
        this.head.next = this.tail;
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) return -1;
        Node node = this.cache.get(key);
        int val = node.val;
        this.remove(node);
        this.insert(node);
        return val;
    }

    public void put(int key, int value) {
        if (!this.cache.containsKey(key)) {
            Node node = new Node(key, value);
            this.insert(node);
            this.cache.put(key, node);
        } else {
            Node node = this.cache.get(key);
            node.val = value;
            this.remove(node);
            this.insert(node);
        }
        if (this.cache.size() > this.capacity) {
            Node node = this.head.next;
            this.remove(node);
            this.cache.remove(node.key);
        }
    }

    private void insert(Node node) {
        Node last = this.tail.prev;
        node.prev = last;
        last.next = node;
        node.next = this.tail;
        this.tail.prev = node;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private class Node {
        private Node prev;
        private Node next;
        private int key;
        private int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
