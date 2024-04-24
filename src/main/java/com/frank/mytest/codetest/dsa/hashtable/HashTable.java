package com.frank.mytest.codetest.dsa.hashtable;

public class HashTable {
    private Node[] arr;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.arr = new Node[capacity];
        this.capacity = capacity;
        this.size = 0;

        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(4);
        table.insert(1, 2);
        System.out.println(table.getCapacity());
        table.insert(2, 3);
        table.insert(0, 99);
        System.out.println(table.getCapacity());
        System.out.println(table.get(1));
        System.out.println(table.get(0));
        System.out.println(table.remove(0));
        System.out.println(table.get(0));
        System.out.println(table.remove(99));
        System.out.println(table.getSize());
    }

    public void insert(int key, int value) {
        int index = this.hash(key);
        Node temp = this.arr[index];
        while (temp.next != null) {
            if (temp.next.key == key) {
                temp.next.value = value;
                return;
            }
            temp = temp.next;
        }
        temp.next = new Node(key, value);
        this.size++;
        if ((double) this.size / this.capacity >= 0.5) {
            this.resize();
        }
    }

    public int get(int key) {
        int index = this.hash(key);
        Node temp = this.arr[index];
        while (temp.next != null) {
            if (temp.next.key == key) {
                return temp.next.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    public boolean remove(int key) {
        int index = this.hash(key);
        Node temp = this.arr[index];
        while (temp.next != null) {
            if (temp.next.key == key) {
                temp.next = temp.next.next;
                this.size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void resize() {
        int newCap = this.capacity << 1;
        Node[] newArr = new Node[newCap];
        Node[] originalArr = this.arr;
        this.arr = newArr;
        this.capacity = newCap;
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        for (Node n : originalArr) {
            // 計算原有 arr 上的節點在新 arr 上的 index 位置並放置
            Node node = n.next;
            while (node != null) {
                int index = this.hash(node.key);
                Node head = this.arr[index];
                Node newNode = new Node(node.key, node.value);
                if (head.next != null) {
                    newNode.next = head.next;
                }
                head.next = newNode;
                node = node.next;
            }
        }
    }

    private int hash(int key) {
        return key % this.capacity;
    }
}

// Singly Linked List
class Node {
    int key;
    int value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

