package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class OpenTheLock {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202")); // 6
        System.out.println(solution.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888")); // -1
    }

    static class Solution {
        // BFS
        public int openLock(String[] deadends, String target) {
//            return bfsV1(deadends, target);
            return bfsV2(deadends, target);
        }

        private int bfsV2(String[] deadends, String target) {
            Set<String> visit = new HashSet<>(Arrays.asList(deadends));
            if (visit.contains("0000")) return -1;

            visit.add("0000");
            Deque<String> queue = new ArrayDeque<>();
            queue.addLast("0000");
            int steps = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String code = queue.pollFirst();
                    if (target.equals(code)) return steps;

                    for (String child : children(code)) {
                        if (visit.contains(child)) continue;
                        visit.add(child);
                        queue.addLast(child);
                    }
                }
                steps++;
            }
            return -1;
        }

        private List<String> children(String code) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                char[] arr = code.toCharArray();
                arr[i] = (char) (((arr[i] - '0' + 1) % 10) + '0');
                res.add(new String(arr));
                arr = code.toCharArray();
                arr[i] = (char) (((arr[i] - '0' - 1 + 10) % 10) + '0');
                res.add(new String(arr));
            }
            return res;
        }

        private int bfsV1(String[] deadends, String target) {
            Set<String> visit = new HashSet<>(Arrays.asList(deadends));
            if (visit.contains("0000")) return -1;

            int[][] diffs = new int[][]{{1, 0, 0, 0}, {-1, 0, 0, 0}, {0, 1, 0, 0}, {0, -1, 0, 0}, {0, 0, 1, 0}, {0, 0, -1, 0}, {0, 0, 0, 1}, {0, 0, 0, -1}};
            int count = 0;
            Deque<int[]> queue = new ArrayDeque<>();
            queue.addLast(new int[]{0, 0, 0, 0});
            visit.add("0000");

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] code = queue.pollFirst();
                    String join = String.join("", Arrays.stream(code).mapToObj(String::valueOf).toList());
                    if (target.equals(join)) return count;

                    for (int[] diff : diffs) {
                        int x = valueAdjust(code[0] + diff[0]);
                        int y = valueAdjust(code[1] + diff[1]);
                        int z = valueAdjust(code[2] + diff[2]);
                        int k = valueAdjust(code[3] + diff[3]);
                        int[] newCode = {x, y, z, k};
                        String neighbor = String.join("", Arrays.stream(newCode).mapToObj(String::valueOf).toList());
                        if (visit.contains(neighbor)) continue;
                        queue.addLast(newCode);
                        visit.add(neighbor);
                    }
                }
                count += 1;
            }
            return -1;
        }

        private int valueAdjust(int n) {
            if (n == 10) return 0;
            if (n < 0) return 9;
            return n;
        }
    }
}
