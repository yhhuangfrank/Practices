package com.frank.mytest.codetest.leetcode.grpah;

import java.util.HashMap;
import java.util.Map;

public class VerifyAnAlienDictionary {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz")); // true
        System.out.println(solution.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz")); // false
    }

    static class Solution {
        public boolean isAlienSorted(String[] words, String order) {
            Map<Character, Integer> score = new HashMap<>();
            char[] chars = order.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                score.put(chars[i], i);
            }
            // compare adjacent words
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];
                int minLen = Math.min(w1.length(), w2.length());
                boolean isPrefix = w1.substring(0, minLen).equals(w2.substring(0, minLen));
                // w2 不可是 w1 的 prefix
                if (w1.length() > w2.length() && isPrefix) return false;

                for (int j = 0; j < minLen; j++) {
                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);
                    // 當遇到不同 char 時，比對排序是否正確
                    if (c1 != c2) {
                        if (score.get(c1) > score.get(c2)) return false;
                        break;
                    }
                }
            }
            return true;
        }

        public boolean isAlienSortedV2(String[] words, String order) {
            Map<Character, Integer> score = new HashMap<>();
            char[] chars = order.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                score.put(chars[i], i);
            }
            // compare adjacent words
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];
                for (int j = 0; j < w1.length(); j++) {
                    if (j == w2.length()) return false; // w2 不可以是 w1 的 prefix
                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);
                    // 當遇到不同 char 時，比對排序是否正確
                    if (c1 != c2) {
                        if (score.get(c1) > score.get(c2)) return false;
                        break;
                    }
                }
            }
            return true;
        }
    }
}
