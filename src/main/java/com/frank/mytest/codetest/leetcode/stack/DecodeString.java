package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
//    Example 1:
//    Input: s = "3[a]2[bc]"
//    Output: "aaabcbc"
//    Example 2:
//    Input: s = "3[a2[c]]"
//    Output: "accaccacc"
//    Example 3:
//    Input: s = "2[abc]3[cd]ef"
//    Output: "abcabccdcdcdef"

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.decodeString("3[a]2[bc]")); // aaabcbc
        System.out.println(sol.decodeString("3[a2[c]]")); // accaccacc
        System.out.println(sol.decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
    }

    static class Solution {
        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                if (c != ']') {
                    stack.addLast(c);
                    continue;
                }
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peekLast() != '[') {
                    char top = stack.pollLast();
                    temp.append(top);
                }
                stack.pollLast();
                temp.reverse();
                String word = temp.toString();
                StringBuilder count = new StringBuilder();
                while (!stack.isEmpty() && stack.peekLast() >= '0' && stack.peekLast() <= '9') {
                    count.append(stack.pollLast());
                }
                count.reverse();
//                System.out.printf("word: %s, count: %s%n", word, count);
                int cnt = Integer.parseInt(count.toString());
                temp.append(word.repeat(cnt - 1));
                for (int i = 0; i < temp.length(); i++) {
                    stack.addLast(temp.charAt(i));
                }
            }
            stack.forEach(c -> res.append(c));
            return res.toString();
        }
    }
}
