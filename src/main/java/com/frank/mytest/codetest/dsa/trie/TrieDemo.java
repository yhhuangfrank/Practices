package com.frank.mytest.codetest.dsa.trie;

public class TrieDemo {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("ape");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("apt"));
        System.out.println(trie.startsWith("aa"));
    }
}
