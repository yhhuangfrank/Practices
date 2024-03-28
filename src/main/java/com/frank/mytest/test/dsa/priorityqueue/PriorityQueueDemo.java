package com.frank.mytest.test.dsa.priorityqueue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueDemo {

    List<QueueNode> nodes = new ArrayList<>();

    // 加入一個節點
    public void enqueue(int value, int priority) {
        QueueNode newNode = new QueueNode(value, priority);
        if (nodes.isEmpty()) {
            nodes.add(newNode);
            return;
        }
        nodes.add(newNode);
        int newIndex = nodes.size() - 1;
        int parentIndex = (newIndex - 1) >> 1;
        QueueNode temp;

        while (parentIndex >= 0 && newNode.priority > nodes.get(parentIndex).priority) {
            temp = nodes.get(newIndex);
            nodes.set(newIndex, nodes.get(parentIndex));
            nodes.set(parentIndex, temp);
            newIndex = parentIndex;
            parentIndex = (newIndex - 1) >> 1;
        }
    }

    // 移除最優先的 node (想法：與 heap sort 相同，換到最後一個 index 後移除，並對根節點做 maxHeapify)
    public QueueNode dequeue() {
        if (nodes.isEmpty()) return null;
        if (nodes.size() == 1) {
            return nodes.remove(0);
        }

        QueueNode removed = nodes.get(0);
        nodes.set(0, nodes.get(nodes.size() - 1));
        nodes.set(nodes.size() - 1, removed);
        nodes.remove(nodes.size() - 1);
        maxHeapify(0);

        return removed;
    }

    private void maxHeapify(int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;

        int largestIndex = index;
        int len = nodes.size() - 1;
        if (l <= len && nodes.get(l).priority > nodes.get(largestIndex).priority) {
            largestIndex = l;
        }
        if (r <= len && nodes.get(r).priority > nodes.get(largestIndex).priority) {
            largestIndex = r;
        }
        if (largestIndex != index) {
            QueueNode node = nodes.get(index);
            nodes.set(index, nodes.get(largestIndex));
            nodes.set(largestIndex, node);
            maxHeapify(largestIndex);
        }
    }

    private void list() {
        for (QueueNode node : nodes) {
            System.out.println(node);
        }
    }

    public static void main(String[] args) {
        PriorityQueueDemo pq = new PriorityQueueDemo();
        pq.enqueue(1, 11);
        pq.enqueue(2, 1);
        pq.enqueue(3, 22);
        pq.enqueue(0, 1000);
        pq.list();
        System.out.println("=========================");
        pq.dequeue();
        pq.list();
        System.out.println("=========================");
        pq.dequeue();
        pq.list();
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private class QueueNode {
        int value;
        int priority;
    }
}
