package com.frank.mytest.codetest.leetcode.dp;

import java.util.Arrays;

/**
 * You are given a string word.
 * Return the maximum number of non-intersecting substrings of word
 * that are at least four characters long and start and end with the same letter.
 *
 * Example 1:
 * Input: word = "abcdeafdef"
 * Output: 2
 * Explanation:
 * The two substrings are "abcdea" and "fdef".
 *
 * Example 2:
 * Input: word = "bcdaaaab"
 * Output: 1
 * Explanation:
 * The only substring is "aaaa". Note that we cannot also choose "bcdaaaab" since it intersects with the other substring.
 *
 * 1 <= word.length <= 2 * 10^5
 * word consists only of lowercase English letters.
 */
public class FindMaximumNumberOfNonIntersectingSubstrings {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubstrings("abcdeafdef")); // 2
        System.out.println(solution.maxSubstrings("bcdaaaab")); // 1
    }

    static class Solution {
        /**
         * memoization
         * time: O(N), 預處理下一次字母出現位置
         * space: O(N)
         */
        public int maxSubstrings(String word) {
            int n = word.length();
            int[] dp = new int[n];
            Arrays.fill(dp, -1);
            // 優化處理 - nextOcc，沒有使用 nextOcc 時間複雜度為 O(N^2)
            // 紀錄從特定位置 i 下一次出現此 word.charAt(i) 的字母的位置為何
            int[][] nextOcc = new int[n + 1][26];
            Arrays.fill(nextOcc[n], -1); // 邊界條件
            for (int i = n - 1; i >= 0; i--) {
                char c = word.charAt(i);
                for (int j = 0; j < 26; j++) {
                    nextOcc[i][c - 'a'] = nextOcc[i + 1][c - 'a']; // 繼承下一層
                }
                nextOcc[i][c - 'a'] = i;
            }
            return dfs(0, word, dp, nextOcc);
        }

        private int dfs(int s, String word, int[] dp, int[][] nextOcc) {
            if (s > word.length() - 4) return 0;
            if (dp[s] != -1) return dp[s];
            int res = dfs(s + 1, word, dp, nextOcc);
            int i = s + 3;
            while (i < word.length()) {
                if (word.charAt(i) == word.charAt(s)) {
                    break;
                }
                i++;
            }
            if (i != word.length()) {
                res = Math.max(res, 1 + dfs(i + 1, word, dp, nextOcc));
            }
            dp[s] = res;
            return res;
        }
    }
}
