package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class AlienDictionary {
//新字典規則:
//字串 a, 字串 b，若字典中 a 排序比 b 小，須滿足其中一個條件：
//1. a 與 b 的有一個字母不同
//2. a字串長度 < b字串長度，且 a 為 b 的 prefix
//給定一字串列，找出新字典中字母排序
//    Input: ["z","o"]
//    Output: "zo"
//    Input: ["hrn","hrf","er","enn","rfnn"]
//    Output: "hernf"

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words1 = new String[]{"z", "o"};
        String[] words2 = new String[]{"hrn", "hrf", "er", "enn", "rfnn"};
        System.out.println(solution.foreignDictionary(words1)); // zo
        System.out.println(solution.foreignDictionary(words2)); // hernf
    }

    static class Solution {
        private Map<Character, Set<Character>> adj;
        private Set<Character> visit;
        private Set<Character> path;
        private List<Character> resultArr;

        public String foreignDictionary(String[] words) {
            adj = new HashMap<>();
            visit = new HashSet<>();
            path = new HashSet<>();
            resultArr = new ArrayList<>();

            for (String w : words) {
                for (int i = 0; i < w.length(); i++) {
                    adj.putIfAbsent(w.charAt(i), new HashSet<>());
                }
            }

            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];
                int minLen = Math.min(w1.length(), w2.length());
                boolean isPrefix = w1.substring(0, minLen).equals(w2.substring(0, minLen));

                if (w1.length() > w2.length() && isPrefix) return "";

                for (int j = 0; j < minLen; j++) {
                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);
                    if (c1 != c2) {
                        adj.get(c1).add(c2);
                        break;
                    }
                }
            }

            for (Character c : adj.keySet()) {
                if (!dfs(c)) return "";
            }

            StringBuilder res = new StringBuilder();
            for (int i = resultArr.size() - 1; i >= 0; i--) {
                res.append(resultArr.get(i));
            }
            return res.toString();
        }

        // topological sort
        private boolean dfs(Character c) {
            if (path.contains(c)) {
                return false;
            }
            if (visit.contains(c)) {
                return true;
            }
            visit.add(c);
            path.add(c);
            for (Character neigh : adj.get(c)) {
                if (!dfs(neigh)) return false;
            }
            path.remove(c);
            resultArr.add(c);
            return true;
        }
    }
}



