package com.frank.mytest.test.leetcode.array.twopointer;

/**
 * 將兩個 string (word1, word2)每個字母交互組成新的 string，並從 word1 開始合併
 * 若 word1 比較長，將剩餘未組成到的字串接到新字串的尾端
 */
public class MergeStringsAlternately {
    public static void main(String[] args) {
        String word1 = "ab";
        String word2 = "pqr";
        System.out.println(mergeAlternately(word1, word2));
    }

    public static String mergeAlternately(String word1, String word2) {
        int l = 0;
        int r = 0;
        boolean isAlternate = false;
        StringBuilder merged = new StringBuilder();

        while(l < word1.length() && r < word2.length()) {
            if(isAlternate) {
                merged.append(word2.charAt(r));
                r++;
            } else {
                merged.append(word1.charAt(l));
                l++;
            }
            isAlternate = !isAlternate;
        }

        if(l < word1.length()) {
            merged.append(word1.substring(l));
        }
        if(r < word2.length()) {
            merged.append(word2.substring(r));
        }
        return merged.toString();
    }
}
