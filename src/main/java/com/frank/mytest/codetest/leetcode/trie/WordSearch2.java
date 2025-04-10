package com.frank.mytest.codetest.leetcode.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 在一個 m x n 的 board 上，每個格子有一個字母。
 * 給定一個個單字的字串組成之陣列，在 board 上找尋可組成的單字有哪些 (board 上只能水平或垂直移動)
 */
public class WordSearch2 {

    public static void main(String[] args) {
        char[][] board = new char[4][4];
        board[0] = new char[] { 'o', 'a', 'a', 'n' };
        board[1] = new char[] { 'e', 't', 'a', 'e' };
        board[2] = new char[] { 'i', 'h', 'k', 'r' };
        board[3] = new char[] { 'i', 'f', 'l', 'v' };
        String[] words = new String[] { "eat", "oath", "pea", "rain" };
        long start = System.currentTimeMillis();
        System.out.println(findWords(board, words));
        long end = System.currentTimeMillis();
        System.out.println("V1: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println(findWordsV2(board, words));
        end = System.currentTimeMillis();
        System.out.println("V2: " + (end - start) + " ms");
    }

    // 解法一： 使用 HashMap 的字典樹
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie(); // 構建字典樹，將所有 words 放入
        for (String w : words) {
            trie.insert(w);
        }
        // 對 board 每一格子進行 dfs
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, trie.tree, "", result);
            }
        }
        return result;
    }

    // 解法二： 使用 array 的字典樹，並將單字保留在節點上
    public static List<String> findWordsV2(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie2 trie = new Trie2(); // 構建字典樹，將所有 words 放入
        for (String w : words) {
            trie.insert(w);
        }
        // 對 board 每一格子進行 dfs
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, trie.root, result);
            }
        }
        return result;
    }

    public static void dfs(int i, int j, char[][] board, TrieNode n, List<String> res) {
        char c = board[i][j];
        if (c == '#' || n.children[c - 'a'] == null)
            return;

        TrieNode nextNode = n.children[c - 'a'];
        if (nextNode.word != null) {
            res.add(nextNode.word);
            nextNode.word = null; // 去除重複結果
        }
        board[i][j] = '#'; // 標記走過路
        if (i - 1 >= 0) {
            dfs(i - 1, j, board, nextNode, res);
        }
        if (j - 1 >= 0) {
            dfs(i, j - 1, board, nextNode, res);
        }
        if (i + 1 < board.length) {
            dfs(i + 1, j, board, nextNode, res);
        }
        if (j + 1 < board[0].length) {
            dfs(i, j + 1, board, nextNode, res);
        }
        board[i][j] = c;
    }

    public static void dfs(
            int i,
            int j,
            char[][] board,
            Map<Character, Object> t,
            String path,
            List<String> result) {
        char c = board[i][j];
        if (t.get(c) == null)
            return; // 沒有在 trie 中

        Map<Character, Object> nextT = (Map<Character, Object>) t.get(c);
        String nextPath = path + c;
        if (nextT.get('#') != null && '#' == (Character) nextT.get('#')) { // 找到結尾符號，有此單字
            result.add(nextPath);
            nextT.remove('#'); // 移除單字 (word 不重複)
        }
        board[i][j] = '*'; // 標記已走過的路 (不重複走)
        if (i - 1 >= 0 && board[i - 1][j] != '*') {
            dfs(i - 1, j, board, nextT, nextPath, result);
        }
        if (i + 1 < board.length && board[i + 1][j] != '*') {
            dfs(i + 1, j, board, nextT, nextPath, result);
        }
        if (j - 1 >= 0 && board[i][j - 1] != '*') {
            dfs(i, j - 1, board, nextT, nextPath, result);
        }
        if (j + 1 < board[0].length && board[i][j + 1] != '*') {
            dfs(i, j + 1, board, nextT, nextPath, result);
        }
        board[i][j] = c; // 回溯
    }
}
