package com.frank.mytest.codetest.leetcode.heap;

import java.util.*;

public class MaxSubsequenceScore {
//    Example 1:
//
//    Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
//    Output: 12
//    Explanation:
//    The four possible subsequence scores are:
//            - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
//            - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
//            - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
//            - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
//    Therefore, we return the max score, which is 12.

    public static void main(String[] args) {
        // 取長度為 k 的 indices, 計算(nums1中對應元素的總和)乘以(nums2中對應元素的最小值)得到 score，並回傳最大score
        Solution sol = new Solution();
        int[] nums1 = new int[]{1, 3, 3, 2};
        int[] nums2 = new int[]{2, 1, 3, 4};
// 按照 nums2 由大到小
//        4,3,2,1
//        2,3,1,3
//        System.out.println(sol.maxScoreV1(Arrays.copyOf(nums1, nums1.length), Arrays.copyOf(nums2, nums1.length), 3)); // 12
        System.out.println(sol.maxScoreV2(Arrays.copyOf(nums1, nums1.length), Arrays.copyOf(nums2, nums1.length), 3)); // 12
        System.out.println(sol.maxScoreV2(new int[]{4, 2, 3, 1, 1}, new int[]{7, 5, 10, 9, 6}, 1)); // 30
//        10,9,7,6,5
//        3,1,4,1,2
    }

    static class Solution {
        private long score = 0;

        // Heap, 用來剔除 nums1 中最小的值
        public long maxScoreV2(int[] nums1, int[] nums2, int k) {
            int n = nums1.length;
            int[][] arrs = new int[n][2];
            for (int i = 0; i < n; i++) {
                arrs[i] = new int[]{nums1[i], nums2[i]}; // 兩個 array 合併
            }
            Arrays.sort(arrs, (a, b) -> b[1] - a[1]); // 按照 nums2 的值由大到小排列，遍歷時該元素即為目前nums2最小值
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 固定 k 大小
            long res = 0;
            long curSum = 0;
            for (int[] a : arrs) {
                minHeap.add(a[0]);
                curSum += a[0];
                if (minHeap.size() > k) {
                    curSum -= minHeap.poll(); // 剔除 nums1 最小值
                }
                if (minHeap.size() == k) {
                    res = Math.max(res, curSum * a[1]);
                }
            }
            return res;
        }

        // brute force, backtracking
        public long maxScoreV1(int[] nums1, int[] nums2, int k) {
            dfs(0, new ArrayList<>(), 0L, Long.MAX_VALUE, k, nums1, nums2);
            return this.score;
        }

        private void dfs(int i, List<Integer> cur, long curSum, long curMin, int k, int[] nums1, int[] nums2) {
            if (cur.size() == k) {
                this.score = Math.max(this.score, curSum * curMin);
                return;
            }
            if (i == nums1.length) {
                return;
            }
            cur.add(nums1[i]);
            curSum += nums1[i];
            dfs(i + 1, cur, curSum, Math.min(curMin, nums2[i]), k, nums1, nums2);
            cur.remove(cur.size() - 1);
            curSum -= nums1[i];
            dfs(i + 1, cur, curSum, curMin, k, nums1, nums2);
        }
    }
}
