package com.frank.mytest.codetest.leetcode.array;

import java.util.*;

/**
 * 檢查一個數獨的現有數字是否符合數獨規則：
 * 1) 同一列 1-9 只出現一次
 * 2) 同一列 1-9 只出現一次
 * 3) 同一 3x3 方格內 1-9 只出現一次
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[9][9];
        board[0] = new char[] {'5','3','.','.','7','.','.','.','.'};
        board[1] = new char[] {'6','.','.','1','9','5','.','.','.'};
        board[2] = new char[] {'.','9','8','.','.','.','.','6','.'};
        board[3] = new char[] {'8','.','.','.','6','.','.','.','3'};
        board[4] = new char[] {'4','.','.','8','.','3','.','.','1'};
        board[5] = new char[] {'7','.','.','.','2','.','.','.','6'};
        board[6] = new char[] {'.','6','.','.','.','.','2','8','.'};
        board[7] = new char[] {'.','.','.','4','1','9','.','.','5'};
        board[8] = new char[] {'.','.','.','.','8','.','.','7','9'};
        System.out.println(new Solution().isValidSudoku(board));
    }

}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // init
        List<Set<Character>> rows = new ArrayList<>();
        List<Set<Character>> cols = new ArrayList<>();
        List<List<Set<Character>>> boxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
        }
        for (int i = 0; i < 3; i++) {
            List<Set<Character>> l = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                l.add(new HashSet<>());
            }
            boxes.add(l);
        }
        // validate
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                char val = board[i][j];
                if (
                    rows.get(i).contains(val) ||
                    cols.get(j).contains(val) ||
                    boxes.get(i / 3).get(j / 3).contains(val)
                )
                    return false;

                rows.get(i).add(val);
                cols.get(j).add(val);
                boxes.get(i / 3).get(j / 3).add(val);
            }
        }
        return true;
    }
}
