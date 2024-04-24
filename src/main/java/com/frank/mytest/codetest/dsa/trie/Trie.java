package com.frank.mytest.codetest.dsa.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 為尚未建立的字母建立節點
            curr.children.computeIfAbsent(c, character -> new TrieNode());
            curr = (TrieNode) curr.children.get(c);
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) return false;
            curr = (TrieNode) curr.children.get(c);
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) return false;
            curr = (TrieNode) curr.children.get(c);
        }
        return true;
    }
}

class TrieNode {
    Map<Character, Object> children;
    boolean isWord;

    public TrieNode() {
        this.children = new HashMap<>();
        isWord = false;
    }
}
