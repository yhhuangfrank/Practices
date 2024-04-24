package com.frank.mytest.codetest.leetcode.heap;

import java.util.ArrayList;
import java.util.List;

public class KthLargest {
    int k;
    MinHeap heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new MinHeap(nums);
    }

    public static void main(String[] args) {
        int[] nums = new int[3];
        nums[0] = -1;
        nums[1] = 2;
        nums[2] = 3;
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(-1));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(3));

        Integer[][] move = new Integer[][] {
                {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1, -1}
        };
    }

    public int add(int val) {
        if (heap.arr.size() < k) {
            heap.push(val);
        } else if (val > heap.arr.get(0)) {
            heap.pop();
            heap.push(val);
        }
        return heap.arr.get(0);
    }


    private class MinHeap {
        List<Integer> arr;

        public MinHeap(int[] nums) {
            this.arr = new ArrayList<>();
            init(nums);
        }

        private void init(int[] nums) {
            if (nums.length == 0) return;
            for (int n : nums) {
                this.arr.add(n);
            }
            for (int i = arr.size() / 2; i >= 0; i--) {
                minHeapify(i);
            }
            int size = this.arr.size();
            while (size > k) {
                pop();
            }
        }

        private void minHeapify(int i) {
            int l = 2 * i + 1;
            int r = l + 1;
            int min = i;

            if (l < arr.size() && arr.get(l) < arr.get(min)) {
                min = l;
            }
            if (r < arr.size() && arr.get(r) < arr.get(min)) {
                min = r;
            }

            if (min != i) {
                swap(min, i);
                minHeapify(min);
            }
        }

        public void push(int val) {
            arr.add(val);

            int curr = arr.size() - 1;
            int parent = (curr - 1) / 2;

            while (parent >= 0 && arr.get(parent) > arr.get(curr)) {
                swap(parent, curr);
                curr = parent;
                parent = (curr - 1) / 2;
            }
        }

        public int pop() {
            int removed = arr.get(0);
            swap(0, arr.size() - 1);
            arr.remove(arr.size() - 1);
            minHeapify(0);
            return removed;
        }

        private void swap(int i, int j) {
            int temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }
}
