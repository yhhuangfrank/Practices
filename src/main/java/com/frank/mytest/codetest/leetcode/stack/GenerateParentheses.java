package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GenerateParentheses {
//    Example 1:
//    Input: n = 3
//    Output: ["((()))","(()())","(())()","()(())","()()()"]
//
//    Example 2:
//    Input: n = 1
//    Output: ["()"]

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generateParenthesis(3));
    }

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            dfs(0, 0, n, "", res);
            return res;
        }

        private void dfs(int left, int right, int n, String cur, List<String> res) {
            if (left < right) return;
            if (left == n && right == n) {
                res.add(cur);
                return;
            }
            if (left < n) {
                dfs(left + 1, right, n, cur + "(", res);
            }
            if (right < n) {
                dfs(left, right + 1, n, cur + ")", res);
            }
        }
    }

}
