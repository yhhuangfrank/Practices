package com.frank.mytest.test.leetcode.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 判斷一個 string 是否為 “回文”，即往前往後讀皆得到相同文字
 * 須先將空格移除，並忽略大小寫
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String s1 = "0P";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome(s1));
        System.out.println(isPalindromeV2(s));
        System.out.println(isPalindromeV2(s1));
    }

    public static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        String s1 = s.toLowerCase();

        while (l < r) {
            char lc = s1.charAt(l);
            char rc = s1.charAt(r);
            if (!isAlphanumeric(lc)) {
                l++;
            } else if (!isAlphanumeric(rc)) {
                r--;
            } else if (lc != rc) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    // 使用 arr 方式，額外的 O(n) space complexity
    public static boolean isPalindromeV2(String s) {
        List<String> arr = Arrays.stream(s.split(""))
                .map(String::toLowerCase)
                .filter(c -> isAlphanumeric(c.charAt(0)))
                .toList();
        int l = 0;
        int r = arr.size() - 1;
        while (l < r) {
            if (!arr.get(l).equals(arr.get(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
