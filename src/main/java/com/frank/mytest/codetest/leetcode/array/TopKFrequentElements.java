package com.frank.mytest.codetest.leetcode.array;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
        System.out.println(Arrays.toString(topKFrequentV2(arr, 2)));
        System.out.println(Arrays.toString(topKFrequentV3(arr, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.merge(n, 1, (oldVal, v) -> oldVal + v);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n2.val - n1.val);
        count.forEach((key, v) -> pq.add(new Node(key, v)));
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.remove().key;
        }
        return res;
    }

    public static int[] topKFrequentV2(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.merge(n, 1, (oldVal, v) -> oldVal + v);
        }
        MaxHeap heap = new MaxHeap();

        count.forEach((key, v) -> heap.push(new Node(key, v)));

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.pop().key;
        }
        return res;
    }

    public static int[] topKFrequentV3(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.merge(n, 1, (oldVal, v) -> oldVal + v);
        }
        List<List<Integer>> arr = new ArrayList<>(); // 表示個數 0 ~ nums.length 的各有哪些數
        for (int i = 0; i < nums.length + 1; i++) {
            arr.add(new ArrayList<>());
        }
        count.forEach((key, v) -> arr.get(v).add(key));
        int[] res = new int[k];
        int incr = 0;
        // 相同個數的可能有多個，但平均來講有 O(n) Time Complexity
        for (int i = arr.size() - 1; i > 0; i--) {
            for (int n : arr.get(i)) {
                res[incr++] = n;
                if (incr == k)
                    return res;
            }
        }
        return res;
    }

    private static class Node {
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private static class MaxHeap {
        List<Node> arr;

        public MaxHeap() {
            this.arr = new ArrayList<>();
        }

        public void push(Node node) {
            this.arr.add(node);
            int curr = this.arr.size() - 1;
            int parent = (curr - 1) >> 1;
            while (parent >= 0 && this.arr.get(parent).val < this.arr.get(curr).val) {
                swap(parent, curr);
                curr = parent;
                parent = (curr - 1) >> 1;
            }
        }

        public Node pop() {
            Node removed = this.arr.get(0);
            swap(0, this.arr.size() - 1);
            this.arr.remove(this.arr.size() - 1);
            maxHeapify(0);
            return removed;
        }

        private void maxHeapify(int index) {
            int l = (index << 1) + 1;
            int r = l + 1;
            int maxIndex = index;
            if (l < this.arr.size() && this.arr.get(l).val > this.arr.get(maxIndex).val) {
                maxIndex = l;
            }
            if (r < this.arr.size() && this.arr.get(r).val > this.arr.get(maxIndex).val) {
                maxIndex = r;
            }
            if (maxIndex != index) {
                swap(maxIndex, index);
                maxHeapify(maxIndex);
            }
        }

        private void swap(int x, int y) {
            Node temp = this.arr.get(x);
            this.arr.set(x, this.arr.get(y));
            this.arr.set(y, temp);
        }

    }
}
