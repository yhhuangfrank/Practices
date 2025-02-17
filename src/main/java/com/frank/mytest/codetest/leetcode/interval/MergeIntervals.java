package com.frank.mytest.codetest.leetcode.interval;

//Example 1:
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//
//Example 2:
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1,3}, {2, 6}, {8, 10}, {15, 18}};
        Solution sol = new Solution();
        System.out.println(Arrays.deepToString(sol.merge(intervals)));
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int[] inter : intervals) {
            int start = inter[0];
            int end = inter[1];
            int[] lastEnd = res.get(res.size() - 1);
            if (start > lastEnd[1]) {
                res.add(inter);
            } else {
                lastEnd[1] = Math.max(lastEnd[1], end);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
