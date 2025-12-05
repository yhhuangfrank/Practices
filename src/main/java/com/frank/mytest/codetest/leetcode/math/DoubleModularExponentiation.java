package com.frank.mytest.codetest.leetcode.math;

import java.util.*;

/**
 * 關鍵的性質：
 * (X * Y) % m = (X % m) * (Y % m) % m
 * <p>
 * Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
 * Output: [0,2]
 * Input: variables = [[39,3,1000,1000]], target = 17
 * Output: []
 * Input: [[2,2,3,2],[1,3,3,2],[3,2,2,3],[3,1,2,3],[1,2,3,1],[2,2,2,2],[2,1,3,1],[3,2,2,2],[2,1,3,1],[3,3,1,3]], target = 0
 * Output: [0,2,3,4,5,6,8]
 */
public class DoubleModularExponentiation {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getGoodIndices(new int[][]{{2, 3, 3, 10}, {3, 3, 3, 1}, {6, 1, 1, 4}}, 2));
        System.out.println(solution.getGoodIndices(new int[][]{{39, 3, 1000, 1000}}, 17));
        System.out.println(solution.getGoodIndices(new int[][]{{2, 2, 3, 2}, {1, 3, 3, 2}, {3, 2, 2, 3}, {3, 1, 2, 3}, {1, 2, 3, 1}, {2, 2, 2, 2}, {2, 1, 3, 1}, {3, 2, 2, 2}, {2, 1, 3, 1}, {3, 3, 1, 3}}, 0));
    }

    static class Solution {
        public List<Integer> getGoodIndices(int[][] variables, int target) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < variables.length; i++) {
                int[] vars = variables[i];
                int a = vars[0];
                int b = vars[1];
                int c = vars[2];
                int m = vars[3];
                int d = 1; // 找個位數的數字
                for (int j = 0; j < b; j++) {
                    d *= a;
                    d %= 10;
                }
                int cur = d % 10;
                // cur = x * m + y
                // cur^c = (x * m + y)^c
                // 根據二項式定理，需判斷是否整除的部分為 C(c, c) * y^c = y^c
                // 所以 cur^c % m = (cur % m)^c % m
                int temp = 1;
                int base = cur % m;
                for (int j = 0; j < c; j++) {
                    temp *= base;
                    temp %= m;
                }
                cur = temp % m;
                if (cur == target) {
                    res.add(i);
                }
            }
            return res;
        }
    }
}
