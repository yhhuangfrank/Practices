package com.frank.mytest.codetest.leetcode.interval;

import java.util.*;

public class MeetingRooms2 {

//    Input: intervals = [(0,40),(5,10),(15,20)]
//    Output: 2
//    day1: (0,40)
//    day2: (5,10),(15,20)
//
//    Input: intervals = [(4,9)]
//    Output: 1
//
//    Input: intervals = [(1,5),(5,10),(10,15),(15,20),(1,20),(2,6)]
//    Output: 3


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Interval> intervalList1 = List.of(new Interval(0, 40), new Interval(5, 10), new Interval(15, 20));
        List<Interval> intervalList2 = List.of(new Interval(4, 9));
        List<Interval> intervalList3 = List.of(new Interval(1, 5), new Interval(5, 10), new Interval(10, 15), new Interval(15, 20), new Interval(1, 20), new Interval(2, 6));

        System.out.println(solution.minMeetingRooms(intervalList1)); // 2
        System.out.println(solution.minMeetingRooms(intervalList2)); // 1
        System.out.println(solution.minMeetingRooms(intervalList3)); // 3
    }

    private static class Solution {
        public int minMeetingRooms(List<Interval> intervals) {
//            return minMeetingRoomsV1(intervals);
            return minMeetingRoomsV2(intervals);
        }

        private int minMeetingRoomsV1(List<Interval> intervals) {
            Map<Integer, Integer> map = new HashMap<>(); // {time: count}, 某一個時刻有多少個會議
            List<Integer> timeList = new ArrayList<>();
            for (Interval interval : intervals) {
                if (!map.containsKey(interval.start)) {
                    map.put(interval.start, 0);
                    timeList.add(interval.start);
                }
                if (!map.containsKey(interval.end)) {
                    map.put(interval.end, 0);
                    timeList.add(interval.end);
                }
                map.put(interval.start, map.get(interval.start) + 1);
                map.put(interval.end, map.get(interval.end) - 1);
            }
            Collections.sort(timeList);
            int prefixSum = 0;
            int maxOverlap = 0;
            for (int time : timeList) {
                prefixSum += map.get(time);
                maxOverlap = Math.max(maxOverlap, prefixSum);
            }
            return maxOverlap;
        }

        private int minMeetingRoomsV2(List<Interval> intervals) {
            List<Integer> start = new ArrayList<>();
            List<Integer> end = new ArrayList<>();

            for (Interval interval : intervals) {
                start.add(interval.start);
                end.add(interval.end);
            }

            Collections.sort(start);
            Collections.sort(end);
            int i = 0;
            int j = 0;
            int rooms = 0;
            int maxRooms = 0;

            while (i < start.size()) {
                if (start.get(i) < end.get(j)) { // 前一個會議還沒有結束，需增加一間
                    rooms++;
                    maxRooms = Math.max(maxRooms, rooms);
                    i++;
                } else {
                    rooms--;
                    j++;
                }
            }

            return maxRooms;
        }
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
    }
}
