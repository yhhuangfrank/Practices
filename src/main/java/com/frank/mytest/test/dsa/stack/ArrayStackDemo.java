package com.frank.mytest.test.dsa.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.traverse();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
//        System.out.println(stack.pop());
        stack.traverse();
    }
}


class ArrayStack {
    private final int maxSize;
    private final int[] arr; // 使用 array 存放值
    private int top = -1; // 初始值為 -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1; // 還是初始值，代表為空
    }

    public boolean isFull() {
        return top == maxSize - 1; // index 為 maxSize - 1 表示 arr 滿了
    }

    // 新增值
    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        arr[++top] = value;
    }

    // 提出值
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return arr[top--];
    }

    // 遍歷
    public void traverse() {
        if (isEmpty()) {
            System.out.println("stack is empty");
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d\n", top - i, arr[i]);
        }
    }
}