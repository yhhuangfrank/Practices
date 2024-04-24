package com.frank.mytest.codetest.leetcode.heap;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

/**
 * 給定一字串，回傳一依照字母出現頻率由大到小排列後的字串 (大小寫視為不同的字母)
 * 若有多個答案，回傳任一答案
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        String s = "abbbaacacf";
        System.out.println(frequencySortV2(s));
    }

    public static String frequencySort(String s) {
        if (s.length() == 1) return s;

        // count char frequency
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            count.merge(s.charAt(i), 1 , (oldValue, v) -> oldValue + v);
        }

        // construct sorted string with priority queue
        PQ pq = new PQ();
        for (Map.Entry<Character, Integer> e : count.entrySet()) {
            Character c = e.getKey();
            Integer frequency = e.getValue();
            pq.enqueue(frequency, String.valueOf(c).repeat(frequency));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            QNode removed = pq.dequeue();
            if (removed != null) {
                sb.append(removed.string);
            }
        }
        return sb.toString();
    }

    // 使用 java.util
    public static String frequencySortV2(String s) {
        if (s.length() == 1) return s;
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            frequencyMap.merge(s.charAt(i), 1, Integer::sum);
        }

        PriorityQueue<QNode> pq = new PriorityQueue<>((n1, n2) -> n2.priority - n1.priority); // 由大到小
        frequencyMap.forEach((character, frequency) -> {
            String string = String.valueOf(character).repeat(frequencyMap.get(character));
            pq.add(new QNode(frequency, string));
        });
        StringBuilder stringBuilder = new StringBuilder();
        while (!pq.isEmpty()) {
            stringBuilder.append(pq.remove().string);
        }
        return stringBuilder.toString();
    }

    private static class PQ {
        List<QNode> nodes = new ArrayList<>();

        public boolean isEmpty() {
            return nodes.isEmpty();
        }

        public void enqueue(int priority, String string) {
            QNode n = new QNode(priority, string);
            if (nodes.isEmpty()) {
                nodes.add(n);
                return;
            }
            nodes.add(n);
            int currIndex = nodes.size() - 1;
            int parentIndex = (currIndex - 1) >> 1;

            while (parentIndex >= 0 && nodes.get(currIndex).priority > nodes.get(parentIndex).priority) {
                QNode temp = nodes.get(currIndex);
                nodes.set(currIndex, nodes.get(parentIndex));
                nodes.set(parentIndex, temp);
                currIndex = parentIndex;
                parentIndex = (currIndex - 1) >> 1;
            }
        }

        public QNode dequeue() {
            if (nodes.isEmpty()) return null;
            if (nodes.size() == 1) return nodes.remove(0);

            QNode removed = nodes.get(0);
            nodes.set(0, nodes.get(nodes.size() - 1));
            nodes.set(nodes.size() - 1, removed);
            nodes.remove(nodes.size() - 1);
            maxHeapify(0);

            return removed;
        }

        private void maxHeapify(int i) {
            int l = (i << 1) + 1;
            int r = l + 1;
            int max = i;

            if (l < nodes.size() && nodes.get(l).priority > nodes.get(max).priority) {
                max = l;
            }
            if (r < nodes.size() && nodes.get(r).priority > nodes.get(max).priority) {
                max = r;
            }
            if (max != i) {
                QNode temp = nodes.get(i);
                nodes.set(i, nodes.get(max));
                nodes.set(max, temp);
                maxHeapify(max);
            }
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class QNode{
        int priority;
        String string;
    }
}
