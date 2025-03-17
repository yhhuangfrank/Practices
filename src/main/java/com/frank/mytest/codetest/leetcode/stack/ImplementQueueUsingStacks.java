package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false
    }

    static class MyQueue {
        private Deque<Integer> left;
        private Deque<Integer> right;

        public MyQueue() {
            this.left = new ArrayDeque<>();
            this.right = new ArrayDeque<>();
        }

        public void push(int x) {
            this.left.addLast(x);
        }

        public int pop() {
            if (this.right.isEmpty()) {
                while (!this.left.isEmpty()) {
                    this.right.addLast(this.left.pollLast());
                }
            }
            return this.right.pollLast();
        }

        public int peek() {
            if (this.right.isEmpty()) {
                while (!this.left.isEmpty()) {
                    this.right.addLast(this.left.pollLast());
                }
            }
            return this.right.peekLast();
        }

        public boolean empty() {
            return this.left.isEmpty() && this.right.isEmpty();
        }
    }

}
