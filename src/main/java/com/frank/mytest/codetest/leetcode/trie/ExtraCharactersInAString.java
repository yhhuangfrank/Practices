package com.frank.mytest.codetest.leetcode.trie;

import java.util.*;

public class ExtraCharactersInAString {
//    Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
//    Output: 1
//    Input: s = "sayhelloworld", dictionary = ["hello","world"]
//    Output: 3

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minExtraChar("leetscode", new String[] { "leet", "code", "leetcode" }));
        System.out.println(solution.minExtraChar("sayhelloworld", new String[] { "hello", "world" }));
    }

    static class Solution {
        public int minExtraChar(String s, String[] dictionary) {
            // Set<String> words = new HashSet<>(Arrays.asList(dictionary));
            // return dfs(0, s, words);
            // return memoization(0, s, words, new HashMap<>());
            return dp(s, dictionary);
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
    }
}
