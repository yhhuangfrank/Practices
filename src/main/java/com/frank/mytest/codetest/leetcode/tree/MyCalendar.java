package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MyCalendar {
    List<int[]> list = new ArrayList<>();
    Node root;
    TreeSet<int[]> events = new TreeSet<>((a, b) -> a[0] - b[0]);

    private class Node {
        int start;
        int end;
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        // [47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]
        // [true,true,false,false,true,false,true,true,true,false]
        List<int[]> bookings = new ArrayList<>();
        bookings.add(new int[]{47, 50});
        bookings.add(new int[]{33, 41});
        bookings.add(new int[]{39, 45});
        bookings.add(new int[]{33, 42});
        bookings.add(new int[]{25, 32});
        bookings.add(new int[]{26, 35});
        bookings.add(new int[]{19, 25});
        bookings.add(new int[]{3, 8});
        bookings.add(new int[]{8, 13});
        bookings.add(new int[]{18, 27});
        List<Boolean> results = List.of(true, true, false, false, true, false, true, true, true, false);
        for (int i = 0; i < bookings.size(); i++) {
            boolean result = results.get(i);
            boolean bookResult = myCalendar.book(bookings.get(i)[0], bookings.get(i)[1]);
            if (result != bookResult) {
                System.out.printf("error in [%s,%s]", bookings.get(i)[0], bookings.get(i)[1]);
            }
            System.out.println(bookResult);
        }
    }

    public boolean book(int startTime, int endTime) {
//        return m1(startTime, endTime);
//        return m2(startTime, endTime); // average O(logN) time
        return m3(startTime, endTime); // O(logN) time
    }

    private boolean m3(int startTime, int endTime) {
        int[] event = new int[]{startTime, endTime};
        int[] prev = events.floor(event);
        int[] next = events.ceiling(event);

        if ((prev != null && prev[1] > startTime) || (next != null && next[0] < endTime)) {
            return false;
        }
        events.add(event);
        return true;
    }

    private boolean m2(int startTime, int endTime) {
        if (this.root == null) {
            this.root = new Node(startTime, endTime);
            return true;
        }
        return this.helper(this.root, startTime, endTime);
    }

    private boolean helper(Node node, int start, int end) {
        if (start >= node.end) {
            if (node.right == null) {
                node.right = new Node(start, end);
                return true;
            }
            return this.helper(node.right, start, end);
        } else if (end <= node.start) {
            if (node.left == null) {
                node.left = new Node(start, end);
                return true;
            }
            return this.helper(node.left, start, end);
        }
        return false;
    }

    public boolean m1(int startTime, int endTime) {
        for (int[] booking : list) {
            if ((startTime < booking[1] && endTime > booking[0])) {
                return false;
            }
        }
        list.add(new int[]{startTime, endTime});
        return true;
    }
}
