package com.frank.mytest.codetest.leetcode.heap;

import java.util.PriorityQueue;

public class ReorganizeString {
    // 給定一字串, 重組為任兩個相鄰的字母不同的新字串

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reorganizeString("aab"));
        System.out.println(solution.reorganizeString("aaab"));
        System.out.println(solution.reorganizeString("aabbcc"));
        System.out.println(solution.reorganizeString("aaabc"));
    }

    static class Solution {
        public String reorganizeString(String s) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a'] += 1;
            }
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]); // [idx, count]
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    maxHeap.add(new int[] {i, count[i]});
                }
            }
            StringBuilder res = new StringBuilder();
            int[] prev = null;
            while (!maxHeap.isEmpty() || prev != null) {
                if (prev != null && maxHeap.isEmpty()) return "";
                int[] poll = maxHeap.poll();
                int i = poll[0];
                res.append((char) ('a' + i));
                poll[1] -= 1;
                if (prev != null) {
                    maxHeap.add(prev);
                    prev = null;
                }
                if (poll[1] > 0) {
                    prev = poll;
                }
            }
            return res.toString();
        }
    }
}
