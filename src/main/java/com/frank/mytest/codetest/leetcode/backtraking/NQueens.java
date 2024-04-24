package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input: n = 4
 * Output:
 * [
 *  [".Q..","...Q","Q...","..Q."],
 *  ["..Q.","Q...","...Q",".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 */
public class NQueens {

    static int[] board;
    static List<List<String>> res = new ArrayList<>();
    static int row = 0;

    public static void main(String[] args) {
        solveNQueens(4);
        System.out.println(res.size());
    }

    /**
     * 用一維陣列表示每一 row 上，皇后要放在 col 幾 (同一 row 的限制就不用檢查了)
     * 剩下同一 col 限制和 對角線限制
     * 在相同對角線上： row 相減和 col 相減的絕對值會相等！
     * 每次在新的 row 上選一個位置 col 來擺皇后
     * 需檢查是否符合 col 限制和對角線限制
     * 1) 不符合則代表此嘗試失敗，需要還原狀態並嘗試下一個
     * 2) 如果符合則要看 row 是不是最後一個，是的話就可以視為答案
     */
    public static List<List<String>> solveNQueens(int n) {
        board = new int[n];
        Arrays.fill(board, -1); // 初始化時，填入不影響判斷的值
        dfs();
        return res;
    }

    public static boolean isValid(int r, int c) {
        for (int i = 0; i < r; i++) {
            if (board[i] == c) return false; // 檢查同一 col
            int dx = i - r;
            int dy = board[i] - c;
            if (Math.abs(dx) == Math.abs(dy)) return false; // 檢查同一對角線
        }
        return true;
    }

    // 輸出是解的盤面
    public static List<String> getBoard() {
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int v : board) {
            sb.append(".".repeat(v));
            sb.append("Q");
            sb.append(".".repeat(board.length - v - 1));
            list.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return list;
    }

    public static void dfs() {
        if (row == board.length) { // 找到結果加入 res 後，返回上一層
            row--;
            res.add(getBoard());
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (isValid(row, i)) {
                board[row] = i;
                row++;
                dfs();
            }
        }
        row--; // 當層遍歷完後，返回上一層
    }
}
