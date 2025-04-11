package com.frank.mytest.codetest.leetcode.array.slidingwindow;

import java.util.*;

public class MinWindowSubstring {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.minWindow("ahffaksfajeeubsne", "jefaa")); // "aksfaje"
    System.out.println(solution.minWindow("caae", "cae")); // "caae"
  }

  static class Solution {
    // sliding window solution, O(n) time complexity
    public String minWindow(String s, String t) {
      if (s.length() < t.length())
        return "";
      Map<Character, Integer> tCount = new HashMap<>();
      for (char c : t.toCharArray()) {
        tCount.put(c, tCount.getOrDefault(c, 0) + 1);
      }
      int l = 0;
      int r = 0;
      int valid = 0; // count the number of characters in the current window that are in t
      int len = Integer.MAX_VALUE; // length of the minimum window
      int start = 0; // start index of the minimum window
      int end = 0; // end index of the minimum window
      Map<Character, Integer> window = new HashMap<>(); // store the characters in the current window

      while (r < s.length()) {
        char c = s.charAt(r);
        if (tCount.containsKey(c)) {
          window.put(c, window.getOrDefault(c, 0) + 1);
          if (window.get(c).equals(tCount.get(c))) {
            valid++;
          }
        }
        // if the current window contains all the characters in t, try to shrink the window
        while (valid == tCount.size()) {
          if (r - l + 1 < len) {
            len = r - l + 1;
            start = l;
            end = r;
          }
          char d = s.charAt(l);
          if (tCount.containsKey(d)) {
            // if the character d is in t, and the number of d in the current window is the same as the number of d in t
            // then we need to shrink the window
            if (window.get(d).equals(tCount.get(d))) {
              valid--;
            }
            window.put(d, window.get(d) - 1);
          }
          l++;
        }
        r++;
      }
      return s.substring(start, end + 1);
    }
  }
}
