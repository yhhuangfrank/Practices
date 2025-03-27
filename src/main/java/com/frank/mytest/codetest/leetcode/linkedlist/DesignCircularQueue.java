package com.frank.mytest.codetest.leetcode.linkedlist;

public class DesignCircularQueue {

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1)); // return True
        System.out.println(myCircularQueue.enQueue(2)); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.enQueue(4)); // return False
        System.out.println(myCircularQueue.Rear());     // return 3
        System.out.println(myCircularQueue.isFull());   // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.enQueue(4)); // return True
        System.out.println(myCircularQueue.Rear());     // return 4
    }

    static class MyCircularQueue {
        private QueueNode head;
        private QueueNode tail;
        private int capacity;
        private int size;

        public MyCircularQueue(int k) {
            this.head = new QueueNode(-1);
            this.tail = new QueueNode(-1);
            this.capacity = k;
            this.size = 0;
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public boolean enQueue(int value) {
            if (this.isFull()) return false;
            QueueNode node = new QueueNode(value);
            QueueNode last = this.tail.prev;
            last.next = node;
            node.prev = last;
            this.tail.prev = node;
            this.size++;
            return true;
        }

        public boolean deQueue() {
            if (this.isEmpty()) return false;
            QueueNode removed = this.head.next;
            this.head.next = removed.next;
            removed.next.prev = this.head;
            this.size--;
            return true;
        }

        public int Front() {
            return this.isEmpty() ? -1 : this.head.next.val;
        }

        public int Rear() {
            return this.isEmpty() ? -1 : this.tail.prev.val;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public boolean isFull() {
            return this.size == this.capacity;
        }

        private class QueueNode {
            int val;
            QueueNode prev;
            QueueNode next;

            public QueueNode(int val) {
                this.val = val;
            }
        }
    }
}
