package com.frank.mytest.codetest.leetcode.heap;

import java.util.PriorityQueue;

public class LongestHappyString {
//    s only contains the letters 'a', 'b', and 'c'.
//    s does not contain any of "aaa", "bbb", or "ccc" as a substring.
//    s contains at most "a" occurrences of the letter 'a'.
//    s contains at most "b" occurrences of the letter 'b'.
//    s contains at most "c" occurrences of the letter 'c'.
//    Input: a = 1, b = 1, c = 7
//    Output: "ccaccbcc"
//    Input: a = 7, b = 1, c = 0
//    Output: "aabaa"

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestDiverseString(1, 1, 7));
        System.out.println(solution.longestDiverseString(7, 1, 0));
    }

    static class Solution {
        public String longestDiverseString(int a, int b, int c) {
            int[] count = new int[] {a, b, c};
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[1] - x[1]);
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    maxHeap.add(new int[] {i, count[i]});
                }
            }
            StringBuilder res = new StringBuilder();
            while (!maxHeap.isEmpty()) {
                int[] poll = maxHeap.poll();
                char ch = (char) ('a' + poll[0]);
                // 跟前兩個字母相同，改取第二多的字母
                int len = res.length();
                if (len > 1 && res.charAt(len - 1) == ch && res.charAt(len - 2) == ch) {
                    if (maxHeap.isEmpty()) break;
                    int[] poll2 = maxHeap.poll();
                    res.append((char) ('a' + poll2[0]));
                    poll2[1] -= 1;
                    if (poll2[1] > 0) {
                        maxHeap.add(poll2);
                    }
                } else {
                    res.append(ch);
                    poll[1] -= 1;
                }
                if (poll[1] > 0) {
                    maxHeap.add(poll);
                }
            }
            return res.toString();
        }
    }
}
