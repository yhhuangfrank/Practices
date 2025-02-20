package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    private Deque<Integer> stack;
    private Deque<Integer> min;

    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.min = new ArrayDeque<>();
    }

    public void push(int val) {
        this.stack.addLast(val);
        if (this.min.isEmpty()) {
            this.min.addLast(val);
        } else {
            this.min.addLast(Math.min(this.min.peekLast(), val));
        }
    }

    public void pop() {
        this.stack.removeLast();
        this.min.removeLast();
    }

    public int top() {
        return this.stack.pollLast();
    }

    public int getMin() {
        return this.min.pollLast();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
