package com.frank.mytest.codetest.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class FindCommonCharacters {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.commonChars(new String[]{"bella", "label", "roller"})); // ["e","l","l"]
        System.out.println(solution.commonChars(new String[]{"cool", "lock", "cook"})); // ["c","o"]
    }

    static class Solution {
        public List<String> commonChars(String[] words) {
            int[] cnt1 = new int[26];
            for (char c : words[0].toCharArray()) {
                cnt1[c - 'a'] += 1;
            }
            for (int i = 1; i < words.length; i++) {
                int[] cnt2 = new int[26];
                for (char c : words[i].toCharArray()) {
                    cnt2[c - 'a'] += 1;
                }
                for (int j = 0; j < cnt1.length; j++) {
                    cnt1[j] = Math.min(cnt1[j], cnt2[j]); // 最小共同次數
                }
            }
            List<String> res = new ArrayList<>();
            for (int i = 0; i < cnt1.length; i++) {
                if (cnt1[i] == 0) continue;
                String c = String.valueOf((char) ('a' + i));
                for (int j = 0; j < cnt1[i]; j++) {
                    res.add(c);
                }
            }
            return res;
        }
    }
}
