package com.frank.mytest.test.leetcode.backtraking;

import java.util.*;

/**
 * 給定一個已經填入一些數字的 9x9 數獨，撰寫程式找出數獨的解 (假定只有一個唯一的解)
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 */
public class SudokuSolver {

    static List<Set<Integer>> rows = new ArrayList<>(); // 紀錄已經填入的列，每一列代表一組 1-9 的集合
    static List<Set<Integer>> cols = new ArrayList<>(); // 紀錄已經填入的行，每一行代表一組 1-9 的集合
    static List<List<Set<Integer>>> squares = new ArrayList<>(); // 紀錄已經填入的每一組 3x3 方格，每一方格代表一組 1-9 的集合
    static Deque<Integer[]> pits = new ArrayDeque<>(); // 紀錄待填的坑的座標

    public static void main(String[] args) {
        char[][] board = new char[9][]; // '.' 代表一開始是空格
        board[0] = new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'};
        board[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        board[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        board[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        board[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        board[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        board[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        board[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        board[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};
        sudokuSolver(board);

        for (int i = 0; i < squares.size(); i++) {
            System.out.println(squares.get(i));
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        // 答案：
//          [5, 3, 4, 6, 7, 8, 9, 1, 2]
//          [6, 7, 2, 1, 9, 5, 3, 4, 8]
//          [1, 9, 8, 3, 4, 2, 5, 6, 7]
//          [8, 5, 9, 7, 6, 1, 4, 2, 3]
//          [4, 2, 6, 8, 5, 3, 7, 9, 1]
//          [7, 1, 3, 9, 2, 4, 8, 5, 6]
//          [9, 6, 1, 5, 3, 7, 2, 8, 4]
//          [2, 8, 7, 4, 1, 9, 6, 3, 5]
//          [3, 4, 5, 2, 8, 6, 1, 7, 9]

    }

    /**
     * 要記下盤面狀況，row 0~8, col 0~8, sqa(方格) 0~8 已經出現哪些數字。如果沒有的話，列為要處理的坑
     * 每次取一個坑，從 1-9 挑一個數字填入試試
     * 如果已經在對應的 row/col/sqa 其任一的位置出現的話表示不能填，必須換下一個
     * 可以填的狀態下，就將對應的狀態設定好，再往下看下一個
     * 如果後續發現這樣填往下走行不通，則回到上一層之前要復原
     * 直到順利填完，沒有剩餘的坑時，表示找到答案
     */
    public static void sudokuSolver(char[][] board) {
        init(board);
        dfs(board);
    }

    /***
     * 遍歷整個棋盤，初始化 rows,cols,squares
     */
    public static void init(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            rows.add(new HashSet<>());
        }
        for (int i = 0; i < board.length; i++) {
            cols.add(new HashSet<>());
        }
        for (int i = 0; i < board.length / 3; i++) {
            List<Set<Integer>> list = new ArrayList<>();
            for (int j = 0; j < board.length / 3; j++) {
                list.add(new HashSet<>());
            }
            squares.add(list);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') { // 空格
                    pits.add(new Integer[]{i, j}); // 坑按照順序放入
                    continue;
                }
                int n = Integer.parseInt(c + "");
                // rows
                rows.get(i).add(n);
                // cols
                cols.get(j).add(n);
                // squares
                int x = i / 3;
                int y = j / 3;
                squares.get(x).get(y).add(n);
            }
        }
    }

    // 新增值到 rows, cols, squares 和 board
    public static void setValue(int r, int c, char value, char[][] board) {
        int v = Integer.parseInt(value + "");
        rows.get(r).add(v);
        cols.get(c).add(v);
        squares.get(r / 3).get(c / 3).add(v);
        board[r][c] = value;
        pits.removeFirst(); // 取出一個坑，表示暫時處理完畢
    }

    public static void unsetValue(int r, int c, char value, char[][] board) {
        int v = Integer.parseInt(value + "");
        rows.get(r).remove(v);
        cols.get(c).remove(v);
        squares.get(r / 3).get(c / 3).remove(v);
        board[r][c] = '.';
        pits.addFirst(new Integer[]{r, c}); // 加回原先位置，下次依舊先處理這個坑 (stack 操作)
    }

    /**
     * 主要遞迴函式
     * 每次取出 pits.removeFirst()，進行迴圈放入 1-9：
     * 1) 若此次要放入的數字 ok，執行 set 動作並遞迴下階段
     * 2) 若下階段的 dfs 返回 false，代表上一層數字無法找到答案，進行 unset
     * 若迴圈結束仍無法返回 true，則返回 false
     */
    public static boolean dfs(char[][] board) {
        if (pits.isEmpty()) return true; // 代表坑洞都填完了
        for (int i = 1; i <= board.length; i++) { // 9x9 數獨
            Integer[] first = pits.peekFirst();
            int x = first[0];
            int y = first[1];
            char c = String.valueOf(i).charAt(0);
            // 檢查目前 i 是否可以作為合法數字放入 first 的座標
            boolean isValid = !rows.get(x).contains(i) && !cols.get(y).contains(i) && !squares.get(x / 3).get(y / 3).contains(i);
            if (isValid) {
                setValue(x, y, c, board);
                if (dfs(board)) return true; // 一路往下填數字
                unsetValue(x, y, c, board); // 若某一階段失敗，回溯
            }
        }
        return false;
    }
}
