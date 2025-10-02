package com.frank.mytest.codetest.leetcode.backtraking;

import java.util.ArrayList;
import java.util.List;

public class Subsets2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsets(new int[]{1, 2, 2}));
    }

    private static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(0, nums, res, new ArrayList<>());
            return res;
        }

        private void dfs(int i, int[] nums, List<List<Integer>> res, List<Integer> lst) {
            if (i == nums.length) {
                res.add(new ArrayList<>(lst));
                return;
            }
            lst.add(nums[i]);
            dfs(i + 1, nums, res, lst);
            lst.remove(lst.size() - 1);

            int j = i + 1;
            while (j < nums.length && nums[j] == nums[i]) {
                j += 1;
            }
            dfs(j, nums, res, lst);
        }
    }
}
