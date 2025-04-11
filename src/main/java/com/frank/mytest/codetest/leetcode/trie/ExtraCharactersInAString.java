package com.frank.mytest.codetest.leetcode.trie;

import java.util.*;

public class ExtraCharactersInAString {
//    Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
//    Output: 1
//    Input: s = "sayhelloworld", dictionary = ["hello","world"]
//    Output: 3

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minExtraChar("leetscode", new String[] { "leet", "code", "leetcode" })); // 1
        System.out.println(solution.minExtraChar("sayhelloworld", new String[] { "hello", "world" })); // 3
    }

    static class Solution {
        public int minExtraChar(String s, String[] dictionary) {
            // Set<String> words = new HashSet<>(Arrays.asList(dictionary));
            // return dfs(0, s, words);
            // return memoization(0, s, words, new HashMap<>());
            // return dp(s, dictionary);
            return minExtraCharByTrie(s, dictionary);
        }

        // dfs solution
        private int dfs(int i, String s, Set<String> words) {
            if (i == s.length()) return 0;
            int res = 1 + dfs(i + 1, s, words);
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (words.contains(sub)) {
                    res = Math.min(res, dfs(j + 1, s, words));
                }
            }
            return res;
        }

        // memoization solution by hashmap as cache
        private int memoization(int i, String s, Set<String> words, Map<Integer, Integer> cache) {
            if (cache.containsKey(i)) return cache.get(i);
            if (i == s.length()) return 0;
            int res = 1 + memoization(i + 1, s, words, cache);
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (words.contains(sub)) {
                    res = Math.min(res, memoization(j + 1, s, words, cache));
                }
            }
            cache.put(i, res);
            return res;
        }

        // dp solution
        private int dp(String s, String[] dictionary) {
            Set<String> words = new HashSet<>(Arrays.asList(dictionary));
            int n = s.length();
            int[] dp = new int[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = dp[i + 1] + 1;
                for (int j = i; j < n; j++) {
                    String sub = s.substring(i, j + 1);
                    if (words.contains(sub)) {
                        dp[i] = Math.min(dp[i], dp[j + 1]);
                    }
                }
            }
            return dp[0];
        }

        // enhanced by Prefix Tree, time complexity: O(n^2)
        private int minExtraCharByTrie(String s, String[] dictionary) {
            Map<Integer, Integer> cache = new HashMap<>();
            cache.put(s.length(), 0);
            Trie trie = new Trie();
            for (String word : dictionary) {
                trie.insert(word);
            }
            return dfsByTrieAndCache(0, s, trie, cache);
        }

        private int dfsByTrieAndCache(int i, String s, Trie trie, Map<Integer, Integer> cache) {
            if (cache.containsKey(i)) return cache.get(i);
            if (i == s.length()) return 0;
            TrieNode node = trie.root;
            int res = 1 + dfsByTrieAndCache(i + 1, s, trie, cache); // skip current char
            for (int j = i; j < s.length(); j++) {
                // check if current char is in trie as a prefix
                char c = s.charAt(j);
                if (node.children[c - 'a'] == null) break; // no way to have a valid word, break
                node = node.children[c - 'a'];
                if (node.isWord) {
                    res = Math.min(res, dfsByTrieAndCache(j + 1, s, trie, cache)); // found a valid word, check next char
                }
            }
            cache.put(i, res);
            return res;
        }

        // create Prefix Tree
        private class Trie {
            private TrieNode root;

            public Trie() {
                root = new TrieNode();
            }
            
            public void insert(String word) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node = node.children[c - 'a'];
                }
                node.isWord = true;
            }
        }

        private class TrieNode {
            private TrieNode[] children;
            private boolean isWord;

            public TrieNode() {
                children = new TrieNode[26];
                isWord = false;
            }
        }
    }
}
