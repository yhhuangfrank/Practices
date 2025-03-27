package com.frank.mytest.codetest.leetcode.binarySearch;

import java.util.*;

public class FindKClosetElements {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findClosestElements(new int[]{1, 5, 10}, 1, 2)); // [1]
        System.out.println(sol.findClosestElements(new int[]{1, 5, 10}, 1, 4)); // [5]
        System.out.println(sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3)); // [1,2,3,4]
        System.out.println(sol.findClosestElements(new int[]{1, 1, 2, 3, 4, 5}, 4, -1)); // [1,1,2,3]
    }

    static class Solution {
        // O(logN + k) time
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int idx = binarySearch(arr, x);
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addLast(arr[idx]);
            int l = idx - 1;
            int r = idx + 1;
            while (deque.size() < k) { // window with size of k
                if (l >= 0 && r < arr.length) {
                    int v1 = Math.abs(arr[l] - x);
                    int v2 = Math.abs(arr[r] - x);
                    if (v1 <= v2) {
                        deque.addFirst(arr[l]);
                        l -= 1;
                    } else {
                        deque.addLast(arr[r]);
                        r += 1;
                    }
                } else if (l >= 0) {
                    deque.addFirst(arr[l]);
                    l -= 1;
                } else {
                    deque.addLast(arr[r]);
                    r += 1;
                }
            }
            return new ArrayList<>(deque);
        }

        private int binarySearch(int[] arr, int x) {
            int l = 0;
            int r = arr.length - 1;
            int m;
            while (l <= r) {
                m = l + (r - l) / 2;
                if (arr[m] == x) return m;
                if (arr[m] > x) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            int idx = l == arr.length ? l - 1 : l;
            while (idx >= 1 && Math.abs(arr[idx - 1] - x) <= Math.abs(arr[idx] - x)) {
                idx -= 1;
            }
            return idx;
        }
    }
}
