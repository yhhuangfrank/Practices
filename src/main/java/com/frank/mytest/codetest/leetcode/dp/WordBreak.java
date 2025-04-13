package com.frank.mytest.codetest.leetcode.dp;

import java.util.*;

public class WordBreak {

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code"))); // true
    System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen"))); // true
    System.out.println(solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); // false
  }

  static class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
      // return wordBreakByBruteForce(s, wordDict);
      // return wordBreakByMemoization(0, s, new HashSet<>(wordDict), new HashMap<>());
      return wordBreakByDP(s, wordDict);
    }

    // brute force, time complexity: O(2^n), space complexity: O(n)
    private boolean wordBreakByBruteForce(String s, List<String> wordDict) {
      Set<String> words = new HashSet<>(wordDict);
      return dfs(0, s, words);
    }

    private boolean dfs(int index, String s, Set<String> words) {
      if (index == s.length()) {
        return true;
      }

      for (int j = index; j < s.length(); j++) {
        String word = s.substring(index, j + 1);
        if (words.contains(word)) {
          if (dfs(j + 1, s, words)) {
            return true;
          }
        }
      }
      return false;
    }

    // memoization, time complexity: O(n * m), space complexity: O(n), n is the length of the string, m is the number of words in the dictionary
    private boolean wordBreakByMemoization(int i, String s, Set<String> words, Map<Integer, Boolean> cache) {
      if (cache.containsKey(i)) {
        return cache.get(i);
      }

      if (i == s.length()) {
        cache.put(i, true);
        return true;
      }

      for (int j = i; j < s.length(); j++) {
        String word = s.substring(i, j + 1);
        if (words.contains(word)) {
          if (wordBreakByMemoization(j + 1, s, words, cache)) {
            cache.put(i, true);
            return true;
          }
        }
      }
      cache.put(i, false);
      return false;
    }

    // dp, time complexity: O(n * m), space complexity: O(n), n is the length of the string, m is the number of words in the dictionary
    private boolean wordBreakByDP(String s, List<String> wordDict) {
      Set<String> words = new HashSet<>(wordDict);
      int n = s.length();
      boolean[] dp = new boolean[n + 1];
      dp[n] = true;
      
      for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
          String word = s.substring(i, j + 1);
          if (!words.contains(word)) continue;
          dp[i] = dp[j + 1];
          if (dp[i]) break;
        }
      }
      return dp[0];
    }
  }
}
