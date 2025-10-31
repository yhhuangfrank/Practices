package com.frank.mytest.codetest.leetcode.heap;

import java.util.*;

/**
 * 給定一正整數數列，從 score = 0 開始，執行下列計算
 * 1. 取沒有被標記的數中最小值，若有複數個，則取 index 較小的
 * 2. 將此數累計到 score。
 * 3. 標記此數和其 index 左右兩邊的數
 * 4. 重複操作直到所有數都被標記
 * 問最後 score 為多少？
 */
public class FindScoreOfAnArrayAfterMarkingAllElements {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findScore(new int[]{2, 1, 3, 4, 5, 2})); // 7
        System.out.println(solution.findScore(new int[]{2, 3, 5, 1, 3, 2})); // 5
    }

    static class Node {
        Node left;
        Node right;
        int val;
        int idx;
        int marked;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    static class Solution {
        public long findScore(int[] nums) {
            Queue<Node> pq = new PriorityQueue<>((a, b) -> {
                if (a.val != b.val) return a.val - b.val;
                return a.idx - b.idx;
            });
            Map<Integer, Node> nodeMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Node node = new Node(nums[i], i);
                if (i - 1 >= 0) {
                    Node prev = nodeMap.get(i - 1);
                    node.left = prev;
                    prev.right = node;
                }
                nodeMap.put(i, node);
                pq.add(node);
            }

            long score = 0;
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                // System.out.printf("val: %s, idx: %s, marked: %s%n", node.val, node.idx, node.marked);
                if (node.marked == 1) continue;
                score += node.val;
                node.marked = 1;
                if (node.left != null) {
                    node.left.marked = 1;
                }
                if (node.right != null) {
                    node.right.marked = 1;
                }
            }
            return score;
        }
    }
}
