package com.frank.mytest.codetest.leetcode.stack;

import java.util.*;

public class EvaluateReversePolishNotation {
//    Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//    Output: 22
//    Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//            = ((10 * (6 / (12 * -11))) + 17) + 5
//            = ((10 * (6 / -132)) + 17) + 5
//            = ((10 * 0) + 17) + 5
//            = (0 + 17) + 5
//            = 17 + 5
//            = 22

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    static class Solution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new ArrayDeque<>();
            Set<String> operators = new HashSet<>(List.of("+", "-", "*", "/"));

            for (String t : tokens) {
                if (operators.contains(t)) {
                    int i = stack.pollLast();
                    int j = stack.pollLast();
                    stack.addLast(calculate(j, i, t));
                } else {
                    stack.addLast(Integer.parseInt(t));
                }
            }
            return stack.peekLast();
        }

        private int calculate(int n1, int n2, String op) {
            return switch (op) {
                case "+" -> n1 + n2;
                case "-" -> n1 - n2;
                case "*" -> n1 * n2;
                case "/" -> n1 / n2;
                default -> 0;
            };
        }
    }

}
