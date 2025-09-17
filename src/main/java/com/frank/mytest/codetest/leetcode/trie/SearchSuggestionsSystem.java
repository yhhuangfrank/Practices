package com.frank.mytest.codetest.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionsSystem {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"));
        System.out.println(solution.suggestedProducts(new String[] {"havana"}, "havana"));
    }

    private static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void addWord(String productName, int id) {
            TrieNode current = this.root;
            for (char c : productName.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                if (current.children[index].suggestedProductIds.size() < 3) { // at most three suggestions
                    current.children[index].suggestedProductIds.add(id);
                }
                current = current.children[index];
            }
        }

        public List<String> search(int endInex, String searchWord, String[] products) {
            TrieNode current = this.root;
            List<String> result = new ArrayList<>();
            for (int i = 0; i <= endInex; i++) {
                int idx = searchWord.charAt(i) - 'a';
                if (current.children[idx] == null) {
                    return result;
                }
                current = current.children[idx];
            }
            for (Integer id : current.suggestedProductIds) {
                result.add(products[id]);
            }
            return result;
        }
    }

    private static class TrieNode {
        TrieNode[] children;
        List<Integer> suggestedProductIds;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.suggestedProductIds = new ArrayList<>();
        }
    }

    private static class Solution {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            return byTrie(products, searchWord);
//            return byTwoPointer(products, searchWord);
        }

        private List<List<String>> byTrie(String[] products, String searchWord) {
            Trie trie = new Trie();
            Arrays.sort(products);
            for (int i = 0; i < products.length; i++) {
                trie.addWord(products[i], i);
            }
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < searchWord.length(); i++) {
                res.add(trie.search(i, searchWord, products));
            }
            return res;
        }


        private List<List<String>> byTwoPointer(String[] products, String searchWord) {
            List<List<String>> res = new ArrayList<>();
            Arrays.sort(products);
            // window, every product in this window is valid (have prefix of searchWord)
            int L = 0;
            int R = products.length - 1;

            for (int i = 0; i < searchWord.length(); i++) {
                char currentChar = searchWord.charAt(i);
                while (L <= R && (products[L].length() <= i || products[L].charAt(i) != currentChar)) {
                    L++;
                }
                while (L <= R && (products[R].length() <= i || products[R].charAt(i) != currentChar)) {
                    R--;
                }
                int windowSize = R - L + 1;
                List<String> list = new ArrayList<>();
                for (int j = 0; j < Math.min(3, windowSize); j++) { // at most 3 words
                    list.add(products[L + j]);
                }
                res.add(list);
            }
            return res;
        }
    }
}
