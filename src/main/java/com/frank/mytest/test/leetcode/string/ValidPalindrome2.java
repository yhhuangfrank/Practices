package com.frank.mytest.test.leetcode.string;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * Example 1:
 * <p>
 * Input: s = "aba"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 * <p>
 * Input: s = "abc"
 * Output: false
 */
public class ValidPalindrome2 {
    public static void main(String[] args) {
        System.out.println(isValid("abca"));
        System.out.println(isValid("abcca"));
        System.out.println(isValid("abccba"));
        System.out.println(isValid("deeee"));
        System.out.println(isValid("eedede"));
        System.out.println(isValid("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));

    }

    private static boolean isValid(String s) {
        if (s.isEmpty() || s.length() == 1) return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (a != b) {
                // 檢查任一情況是回文
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome(String s, int l, int r) {
        if (s.isEmpty() || s.length() == 1) return true;

        while (l < r) {
            char a = s.charAt(l++);
            char b = s.charAt(r--);
            if (a != b) return false;
        }
        return true;
    }
}
