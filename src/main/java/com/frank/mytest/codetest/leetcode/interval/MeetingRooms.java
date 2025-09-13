package com.frank.mytest.codetest.leetcode.interval;

import java.util.List;

public class MeetingRooms {
//    Input: intervals = [(0,30),(5,10),(15,20)]
//    Output: false
//
//    Input: intervals = [(5,8),(9,15)]
//    Output: true

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canAttendMeetings(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)))); // false
        System.out.println(solution.canAttendMeetings(List.of(new Interval(5, 8), new Interval(9, 15)))); // true
    }

    private static class Solution {
        public boolean canAttendMeetings(List<Interval> intervals) {
            if (intervals.size() < 2) return false;
            List<Interval> sortedIntervals = intervals.stream().sorted((a, b) -> a.start - b.start).toList();

            for (int i = 1; i < sortedIntervals.size(); i++) {
                Interval prev = sortedIntervals.get(i - 1);
                Interval curr = sortedIntervals.get(i);
                if (curr.start < prev.end) return false;
            }

            return true;
        }
    }

    private static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
