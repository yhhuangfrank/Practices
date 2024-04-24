package com.frank.mytest.codetest.leetcode.array.slidingwindow;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    // time : O(n), space : O(n)
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            max = Math.max(max, set.size());
            r++;
        }
        return max;
    }
}
