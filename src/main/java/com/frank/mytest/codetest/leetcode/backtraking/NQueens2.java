package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.Arrays;

public class NQueens2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalNQueens(4)); // 2
        System.out.println(solution.totalNQueens(1)); // 1
        System.out.println(solution.totalNQueens(8)); // 92
    }

    static class Solution {
        public int totalNQueens(int n) {
            int[] board = new int[n];
            Arrays.fill(board, -1);
            return dfs(0, board);
        }

        private int dfs(int i, int[] board) {
            if (i == board.length) return 1;
            int res = 0;
            for (int j = 0; j < board.length; j++) {
                board[i] = j;
                if (isValid(i, board)) {
                    res += dfs(i + 1, board);
                }
                board[i] = -1;
            }
            return res;
        }

        private boolean isValid(int i, int[] board) {
            for (int r = 0; r < i; r++) {
                // same column
                if (board[i] == board[r]) return false;
                // same diagonal
                if (Math.abs(i - r) == Math.abs(board[i] - board[r])) return false;
            }
            return true;
        }
    }
}
