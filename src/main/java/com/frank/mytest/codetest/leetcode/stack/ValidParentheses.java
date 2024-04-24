package com.frank.mytest.codetest.leetcode.stack;

import java.util.*;

/**
 * 給一串文字，只包含括號。
 * 判斷是否符合括號兩兩相對
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
    }

    /**
     * 準備一個 map，作為左右括號對應
     * 準備兩個左括號、右括號的集合
     * 準備一個 stack，用於 LIFO 的對應左右括號
     * 遍歷文字，當遇到左括號時，放入 stack，遇到右括號時 peek 後看是否對應
     * 1) 若不能則不是 valid
     * 2) 若可以則從 stack 拿出左括號
     */
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Set<Character> l = map.keySet();
        Set<Character> r = new HashSet<>(map.values());
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            Character s1 = s.charAt(i);
            if (l.contains(s1)) { // 左括號
                stack.addFirst(s1);
            } else if (r.contains(s1)) { // 右括號
                if (stack.isEmpty()) return false;
                Character peek = stack.peekFirst();
                if (!map.getOrDefault(peek, ' ').equals(s1)) return false;
                stack.removeFirst();
            }
        }
        return stack.isEmpty(); // 沒有配對完即為 invalid
    }
}
