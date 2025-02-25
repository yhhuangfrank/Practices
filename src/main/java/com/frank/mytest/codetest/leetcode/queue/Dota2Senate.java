package com.frank.mytest.codetest.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dota2Senate {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.predictPartyVictory("DDRRR")); // Dire
        System.out.println(sol.predictPartyVictory("RD")); // Radiant
        System.out.println(sol.predictPartyVictory("RDD")); // Dire
        System.out.println(sol.predictPartyVictory("RDDR")); // Radiant
        System.out.println(sol.predictPartyVictory("DRRDRDRDRDDRDRDR")); // Radiant
    }

    static class Solution {
        // R: Radiant
        // D: Dire
        public String predictPartyVictory(String senate) {
            int n = senate.length();
            Deque<Integer> r = new ArrayDeque<>();
            Deque<Integer> d = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                char c = senate.charAt(i);
                if (c == 'R') {
                    r.addLast(i);
                } else {
                    d.addLast(i);
                }
            }
            int idx = n;
            while (!r.isEmpty() && !d.isEmpty()) {
                if (r.peekFirst() < d.peekFirst()) {
                    d.pollFirst();
                    r.pollFirst();
                    r.addLast(idx);
                } else {
                    d.pollFirst();
                    r.pollFirst();
                    d.addLast(idx);
                }
                idx += 1;
            }
            return !r.isEmpty() ? "Radiant" : "Dire";
        }
    }
    // DRRDRDRDRDDRDRDR
    // D
}
