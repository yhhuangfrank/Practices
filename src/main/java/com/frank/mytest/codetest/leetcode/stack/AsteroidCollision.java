package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class AsteroidCollision {
//    Input: asteroids = [5,10,-5]
//    Output: [5,10]
//
//    Input: asteroids = [8,-8]
//    Output: []
//
//    Input: asteroids = [10,2,-5]
//    Output: [10]

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = new int[]{5, 10, -5};
        int[] arr2 = new int[]{8, -8};
        int[] arr3 = new int[]{10, 2, -5};
        System.out.println(Arrays.toString(sol.asteroidCollision(arr1)));
        System.out.println(Arrays.toString(sol.asteroidCollision(arr2)));
        System.out.println(Arrays.toString(sol.asteroidCollision(arr3)));
    }

    static class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int n : asteroids) {
                boolean isExist = true;
                while (!stack.isEmpty() && n < 0 && stack.peekLast() > 0) {
                    int diff = stack.peekLast() + n;
                    if (diff < 0) {
                        stack.pollLast();
                    } else {
                        isExist = false;
                        if (diff == 0) stack.pollLast();
                        break;
                    }
                }
                if (isExist) {
                    stack.addLast(n);
                }
            }
            return stack.stream().mapToInt(i -> i).toArray();
        }
    }
}
