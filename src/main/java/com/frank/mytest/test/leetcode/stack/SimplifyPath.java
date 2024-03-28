package com.frank.mytest.test.leetcode.stack;

import java.util.*;

/**
 * Example 1:
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * <p>
 * Example 2:
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * <p>
 * Example 3:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * 給定一 unix 路徑文字，將其改為標準路徑
 * 1) 沒有相對路徑的 "." 符號
 * 2) 結尾沒有 "/"
 * 3) 目錄之間只有一個 "/"
 */
public class SimplifyPath {
    public static void main(String[] args) {
        String s1 = "/home//foo/";
        String s2 = "/../";
        String s3 = "/a//b////c/d//././/..";
        System.out.println(simplifyPathV3(s1));
        System.out.println(simplifyPathV3(s2));
        System.out.println(simplifyPathV3(s3));
    }

    /**
     * 遇到 "." 表示留在當層
     * 遇到 ".." 表示回到上一層
     * 若有多個 "/" 只留下一個
     */
    public static String simplifyPath(String path) {
        boolean isSlashEnd = '/' == path.charAt(path.length() - 1);
        String newPath = isSlashEnd ? path : path.concat("/"); // 若沒有 "/" 結尾則補上
        StringBuilder sb = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();
        int tempIndex = 0; // 指向上一個 "/" 位置

        // 將 deque 作為 stack 使用
        for (int i = 1; i < newPath.length(); i++) {
            if (newPath.charAt(i) != '/') continue;

            if (i - tempIndex != 1){
                String s = newPath.substring(tempIndex + 1, i);
                if ("..".equals(s) && !deque.isEmpty()) {
                    deque.removeFirst();
                } else if (!".".equals(s) && !"..".equals(s)) {
                    deque.addFirst(s);
                }
            }
            tempIndex = i;
        }
        if (deque.isEmpty()) return sb.append("/").toString(); // 表示中間無任何目錄

        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.removeLast()); // 從 stack 底部開始拿
        }

        return sb.toString();
    }


    // 解法二：使用 List (但速度較慢)
    public static String simplifyPathV2(String path) {
        String[] split = path.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (String s : split) {
            String trim = s.trim();
            if ("..".equals(trim) && !list.isEmpty()) {
                list.remove(list.size() - 1);
            } else if (!"..".equals(trim) && !".".equals(trim) && !trim.isEmpty()) {
                list.add(s);
            }
        }
        stringBuilder.append("/");

        if (list.isEmpty()) {
            return stringBuilder.toString();
        }

        return stringBuilder.append(String.join("/", list)).toString();
    }

    // 結合解法一和二
    public static String simplifyPathV3(String path) {
        String[] split = path.split("/");
        StringBuilder sb = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();

        // 將 deque 作為 stack 使用
        for (String s : split) {
            String trim = s.trim();
            if ("..".equals(trim)) {
                if (!deque.isEmpty()) {
                    deque.removeFirst();
                }
                continue;
            }
            if (!".".equals(trim) && !trim.isEmpty()) {
                deque.addFirst(trim);
            }
        }
        if (deque.isEmpty()) return sb.append("/").toString(); // 表示中間無任何目錄

        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.removeLast()); // 從 stack 底部開始拿
        }

        return sb.toString();
    }
}
