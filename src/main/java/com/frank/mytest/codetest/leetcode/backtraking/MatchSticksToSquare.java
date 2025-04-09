package com.frank.mytest.codetest.leetcode.backtraking;


import java.util.Arrays;

public class MatchSticksToSquare {
//    1 <= matchsticks.length <= 15
//    1 <= matchsticks[i] <= 10^8

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(solution.makesquare(new int[]{3, 3, 3, 3, 4}));
        System.out.println(solution.makesquare(new int[]{1, 5, 1, 5, 1, 5, 6}));
    }

    static class Solution {
        public boolean makesquare(int[] matchsticks) {
            int sum = 0;
            for (int n : matchsticks) {
                sum += n;
            }
            if (sum % 4 != 0) return false; // 不能組成正方形
            int[] sides = new int[4]; // 區分上下左右的邊長
            Arrays.sort(matchsticks);
            return dfs(matchsticks.length - 1, sides, sum / 4, matchsticks); // 排序後從長的火柴開始排
        }

        private boolean dfs(int i, int[] sides, int target, int[] matchsticks) {
            if (i == -1) return true;
            for (int j = 0; j < 4; j++) {
                if (sides[j] + matchsticks[i] > target) continue;
                sides[j] += matchsticks[i];
                if (dfs(i - 1, sides, target, matchsticks)) return true;
                sides[j] -= matchsticks[i];
            }
            return false;
        }
    }
}
