package com.frank.mytest.codetest.dsa.linkedList;

import lombok.Getter;
import lombok.ToString;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        System.out.println(linkedList.isEmpty());
        linkedList.add(1);
        linkedList.add(2);
        System.out.println(linkedList.getFirst());
    }
}

@Getter
@ToString
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

    public void remove(int value) {
        if (isEmpty()) return;
        Node current = first;
        Node next = first.next;

        // 刪除的是 first
        if (first.value == value) {
            this.first = next;
            size--;
            return;
        }

        while (true) {
            if (next == null) {
                if (size == 1 && first.value == value) {
                    this.first = null;
                    size--;
                }
                break;
            }
            if (next.value == value) {
                current.next = next.next;
                size--;
                break;
            }
            current = current.next;
            next = next.next;
        }
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

    @ToString
    static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
