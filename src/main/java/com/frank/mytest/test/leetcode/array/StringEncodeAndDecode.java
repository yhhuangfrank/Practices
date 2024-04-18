package com.frank.mytest.test.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class StringEncodeAndDecode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new ArrayList<>(List.of("leet", "code"));
        String encode = solution.encode(list);
        System.out.println(solution.decode(encode));
        System.out.println("=======================");
        String encodeV2 = solution.encodeV2(list);
        System.out.println(solution.decodeV2(encodeV2));
    }


    private static class Solution {
        int[] arr;

        public String encode(List<String> strs) {
            this.arr = new int[strs.size()];
            int i = 0;
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                this.arr[i] = s.length();
                sb.append(s);
                i++;
            }
            return sb.toString();
        }

        // 解法二，不使用額外的陣列，改儲存 (長度 + #) 加入到 encode string
        public String encodeV2(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(s.length());
                sb.append("#");
                sb.append(s);
            }
            return sb.toString();
        }

        public List<String> decode(String str) {
            List<String> res = new ArrayList<>();
            int s = 0;
            for (int n : this.arr) {
                if (n == 0) {
                    res.add("");
                } else {
                    int end = s + n;
                    res.add(str.substring(s, end));
                    s = end;
                }
            }
            return res;
        }

        public List<String> decodeV2(String str) {
            List<String> res = new ArrayList<>();
            int s = 0;
            while (s < str.length()) {
                char c = str.charAt(s);
                if (c - '0' >= 0 && c - '0' <= 9) {
                    int temp = s + 1;
                    while (str.charAt(temp) != '#') {
                        temp++;
                    }
                    int len = Integer.parseInt(str.substring(s, temp));
                    res.add(str.substring(temp + 1, temp + len + 1));
                    s = temp + len + 1;
                }
            }
            return res;
        }
    }
}
