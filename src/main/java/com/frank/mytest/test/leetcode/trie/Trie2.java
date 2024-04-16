package com.frank.mytest.test.leetcode.trie;


/**
 * 使用 array 構建
 */
class TrieNode {
    String word; // 結尾時，儲存單字
    TrieNode[] children = new TrieNode[26]; // 26 個英文字母
}
public class Trie2 {
    TrieNode root = new TrieNode();
    char START_CHAR = 'a';

    public void insert(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.children[c - START_CHAR] == null) {
                temp.children[c - START_CHAR] = new TrieNode();
            }
            temp = temp.children[c - START_CHAR];
        }
        temp.word = word;
    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.children[c - START_CHAR] == null) return false;
            temp = temp.children[c - START_CHAR];
        }
        return temp.word != null;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (temp.children[c - START_CHAR] == null) return false;
            temp = temp.children[c - START_CHAR];
        }
        return true;
    }
}
