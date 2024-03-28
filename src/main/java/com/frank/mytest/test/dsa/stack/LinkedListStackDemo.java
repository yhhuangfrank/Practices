package com.frank.mytest.test.dsa.stack;

import lombok.Getter;
import lombok.ToString;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
//        stack.traverse();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
//        System.out.println(stack.pop());
        stack.traverse();
    }
}

class LinkedListStack {
    private final int maxSize;
    private final LinkedList list;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        list = new LinkedList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return maxSize == list.getSize();
    }

    // 新增值
    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        list.add(value);
    }

    // 提出值
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return list.remove();
    }

    // 遍歷
    public void traverse() {
        if (isEmpty()) return;
        list.traverse();
    }

}

@Getter
class LinkedList {
    private int size;
    private Node first;

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 新的 Node 加到 first
     * @param value
     */
    public void add(int value) {
        Node newNode = new Node(value);
        if (!isEmpty()) {
            newNode.next = first;
        }
        this.first = newNode;
        size++;
    }

    /**
     * 移除 first
     */
    public Integer remove() {
        if (isEmpty()) {
            return null;
        };
        int value = first.value;
        this.first = first.next;
        size--;
        return value;
    }

    public void traverse() {
        if (isEmpty()) return;
        Node current = first;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    @ToString
    static class Node {
        private final int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}

