package com.frank.mytest.codetest.leetcode.bfs;

import java.util.*;

public class SplitAndMergeArrayTransformation {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSplitMerge(new int[]{3, 1, 2}, new int[]{1, 2, 3})); // 1
//        System.out.println(solution.minSplitMerge(new int[]{1, 1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1, 1})); // 3
    }

    static class Solution {
        public int minSplitMerge(int[] nums1, int[] nums2) {
            // 可以 brute force 因為 nums1, nums2 最長是6，並且 array 變化最多可能為 6! = 720種
            List<Integer> numsList1 = new ArrayList<>(); // 轉換為 List 可用於 HashMap 的 key
            List<Integer> numsList2 = new ArrayList<>();
            int n = nums1.length;
            for (int num : nums1) {
                numsList1.add(num);
            }
            for (int num : nums2) {
                numsList2.add(num);
            }
            Map<List<Integer>, Integer> minStep = new HashMap<>(); // 每個組合最短步驟
            minStep.put(numsList1, 0);
            // 使用類似 BFS 走到某一種 array 組合
            Deque<List<Integer>> q = new ArrayDeque<>();
            q.add(numsList1);

            while (!q.isEmpty()) {
                List<Integer> lst = q.pollFirst();
                // split every subArray
                for (int i = 0; i < n; i++) {
                    for (int j = i; j < n; j++) {
                        List<Integer> subArr1 = lst.subList(i, j + 1);
                        List<Integer> subArr2 = this.getRemainList(i, j, lst);
                        for (int k = 0; k <= subArr2.size(); k++) {
                            List<Integer> res = this.merge(subArr1, subArr2, k);
                            System.out.printf("res: %s%n", res);
                            if (!minStep.containsKey(res)) {
                                minStep.put(res, minStep.get(lst) + 1); // 從 lst 衍伸的 subArray, 次數加一
                                q.addLast(res);
                            }
                        }
                    }
                }
            }

            return minStep.get(numsList2);
        }

        private List<Integer> getRemainList(int start, int end, List<Integer> lst) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                if (i < start || i > end) {
                    res.add(lst.get(i));
                }
            }
            return res;
        }

        // 定義 merge 操作後結果
        private List<Integer> merge(List<Integer> arr1, List<Integer> arr2, int pos) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < arr2.size(); i++) {
                if (i == pos) {
                    res.addAll(arr1);
                }
                res.add(arr2.get(i));
            }
            System.out.printf("arr1: %s, arr2: %s, pos: %s %n", arr1, arr2, pos);
            if (pos == arr2.size()) {
                res.addAll(arr1);
            }
            return res;
        }
    }
}
