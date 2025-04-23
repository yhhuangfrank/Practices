package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(new int[]{2, 3, 5}, 8));
    }

    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(0, candidates, target, new ArrayDeque<>(), 0, res);
            return res;
        }

        private void dfs(int i, int[] candidates, int target, Deque<Integer> stack, int curSum, List<List<Integer>> res) {
            if (i == candidates.length || curSum > target) return;
            if (curSum == target) {
                res.add(new ArrayList<>(stack));
                return;
            }
            stack.addLast(candidates[i]);
            dfs(i, candidates, target, stack, curSum + candidates[i], res);
            stack.pollLast();
            dfs(i + 1, candidates, target, stack, curSum, res);
        }
    }
}
