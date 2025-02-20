package com.frank.mytest.codetest.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlusOne {
//    Example 1:
//    Input: digits = [1,2,3]
//    Output: [1,2,4]
//    Explanation: The array represents the integer 123.
//    Incrementing by one gives 123 + 1 = 124.
//    Thus, the result should be [1,2,4].
//
//    Example 2:
//    Input: digits = [4,3,2,1]
//    Output: [4,3,2,2]
//    Explanation: The array represents the integer 4321.
//    Incrementing by one gives 4321 + 1 = 4322.
//    Thus, the result should be [4,3,2,2].
//
//    Example 3:
//    Input: digits = [9]
//    Output: [1,0]
//    Explanation: The array represents the integer 9.
//    Incrementing by one gives 9 + 1 = 10.
//    Thus, the result should be [1,0].

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.plusOne(new int[]{4, 3, 2, 2})));
        System.out.println(Arrays.toString(sol.plusOne(new int[]{9})));
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            int carry = 0;
            List<Integer> res = new ArrayList<>();
            for (int i = digits.length - 1; i >= 0; i--) {
                int total = i == digits.length - 1 ? carry + digits[i] + 1 : carry + digits[i];
                int newD = total % 10;
                carry = total / 10;
                res.add(newD);
            }
            if (carry == 1) {
                res.add(1);
            }
            Collections.reverse(res);
            return res.stream().mapToInt(i -> i).toArray();
        }
    }
}


