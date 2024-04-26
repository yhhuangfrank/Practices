package com.frank.mytest.codetest.leetcode.array.slidingwindow;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        String s3 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2));
        System.out.println(checkInclusion(s1, s3));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false; // edge case
        // 26 個字母
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        int l = 0;
        int r = 0;
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a'] += 1;
            s2Count[s2.charAt(i) - 'a'] += 1;
            r += 1;
        }
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            matches += s1Count[i] == s2Count[i] ? 1 : 0;
        }
        if (matches == 26)
            return true;

        while (r < s2.length()) {
            int lIndex = s2.charAt(l) - 'a';
            int rIndex = s2.charAt(r) - 'a';
            s2Count[lIndex] -= 1;
            if (s2Count[lIndex] == s1Count[lIndex]) {
                matches += 1;
            } else if (s2Count[lIndex] + 1 == s1Count[lIndex]) {
                matches -= 1;
            }
            s2Count[rIndex] += 1;
            if (s2Count[rIndex] == s1Count[rIndex]) {
                matches += 1;
            } else if (s2Count[rIndex] - 1 == s1Count[rIndex]) {
                matches -= 1;
            }
            if (matches == 26)
                return true;
            r += 1;
            l += 1;
        }
        return false;
    }

}
