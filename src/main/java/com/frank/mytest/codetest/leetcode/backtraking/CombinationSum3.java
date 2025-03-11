package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSum3 {
//    Input: k = 3, n = 7
//    Output: [[1,2,4]]
//    Explanation:
//            1 + 2 + 4 = 7
//    There are no other valid combinations.
//    Input: k = 3, n = 9
//    Output: [[1,2,6],[1,3,5],[2,3,4]]
//    Explanation:
//            1 + 2 + 6 = 9
//            1 + 3 + 5 = 9
//            2 + 3 + 4 = 9
//    There are no other valid combinations.

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum3(3, 7));
        System.out.println(solution.combinationSum3(3, 9));
        System.out.println(solution.combinationSum3(9, 45));
    }

    static class Solution{
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(1, k, n, new ArrayDeque<>(), 0, res);
            return res;
        }

        private void dfs(int i, int k, int n, Deque<Integer> stack, int curSum, List<List<Integer>> res) {
            if (stack.size() > k) return;
            if (stack.size() == k && curSum == n) {
                res.add(new ArrayList<>(stack));
                return;
            }
            for (int j = i; j <= 9; j++) {
                stack.addLast(j);
                dfs(j + 1, k, n, stack, curSum + j, res);
                stack.pollLast();
            }
        }
    }
}
