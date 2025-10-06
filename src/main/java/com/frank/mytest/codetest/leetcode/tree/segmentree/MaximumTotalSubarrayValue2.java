package com.frank.mytest.codetest.leetcode.tree.segmentree;

import java.util.*;

public class MaximumTotalSubarrayValue2 {

//    Input: nums = [1,3,2], k = 2
//    Output: 4
//
//    Input: nums = [4,2,5,1], k = 3
//    Output: 12
//    One optimal approach is:
//    Choose nums[0..3] = [4, 2, 5, 1]. The maximum is 5 and the minimum is 1, giving a value of 5 - 1 = 4.
//    Choose nums[1..3] = [2, 5, 1]. The maximum is 5 and the minimum is 1, so the value is also 4.
//    Choose nums[2..3] = [5, 1]. The maximum is 5 and the minimum is 1, so the value is again 4.
//    Adding these gives 4 + 4 + 4 = 12.

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxTotalValue(new int[]{1, 3, 2}, 2)); // 4
        System.out.println(solution.maxTotalValue(new int[]{4, 2, 5, 1}, 3)); // 12
        // 22730856675
        System.out.println(solution.maxTotalValue(new int[]{504031982, 162779399, 660026328, 409739126, 162044878, 541123288, 283308765, 886389922, 875362200, 38728331, 236956052, 202990163, 943757180, 803518738, 947962598, 441918533, 527434810, 291905233, 648372703, 191746874, 627150040, 113414778, 135780161, 529813409, 620409897, 452676860}, 25));
    }

    static class Solution {
        static class SegmentTree {
            SegmentTree left;
            SegmentTree right;
            int minValue;
            int maxValue;
            int L;
            int R;

            public SegmentTree(int L, int R, int minValue, int maxValue) {
                this.L = L;
                this.R = R;
                this.minValue = minValue;
                this.maxValue = maxValue;
            }

            public SegmentTree(int[] nums) {
                SegmentTree tree = this.build(nums, 0, nums.length - 1);
                this.left = tree.left;
                this.right = tree.right;
                this.minValue = tree.minValue;
                this.maxValue = tree.maxValue;
                this.L = tree.L;
                this.R = tree.R;
            }

            private SegmentTree build(int[] nums, int l, int r) {
                if (l == r) {
                    return new SegmentTree(l, r, nums[l], nums[r]);
                }
                SegmentTree root = new SegmentTree(l, r, Integer.MAX_VALUE, Integer.MIN_VALUE);
                int m = l + (r - l) / 2;
                root.left = this.build(nums, l, m);
                root.right = this.build(nums, m + 1, r);
                root.minValue = Math.min(root.left.minValue, root.right.minValue);
                root.maxValue = Math.max(root.left.maxValue, root.right.maxValue);
                return root;
            }

            public int queryMax(int l, int r) {
                if (this.L == l && this.R == r) {
                    return this.maxValue;
                }
                int m = this.L + (this.R - this.L) / 2;
                if (l > m) {
                    return this.right.queryMax(l, r);
                } else if (r <= m) {
                    return this.left.queryMax(l, r);
                }
                return Math.max(this.left.queryMax(l, m), this.right.queryMax(m + 1, r));
            }

            public int queryMin(int l, int r) {
                if (this.L == l && this.R == r) {
                    return this.minValue;
                }
                int m = this.L + (this.R - this.L) / 2;
                if (l > m) {
                    return this.right.queryMin(l, r);
                } else if (r <= m) {
                    return this.left.queryMin(l, r);
                }
                return Math.min(this.left.queryMin(l, m), this.right.queryMin(m + 1, r));
            }
        }

        static class Node {
            int l;
            int r;
            int val;

            public Node(int l, int r, int val) {
                this.l = l;
                this.r = r;
                this.val = val;
            }
        }

        public long maxTotalValue(int[] nums, int k) {
            SegmentTree tree = new SegmentTree(nums); // 搜尋某段範圍內的最大/最小值，以 logN time
            Queue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.val - a.val);
            Node rootNode = new Node(0, nums.length - 1, tree.maxValue - tree.minValue);
            maxHeap.add(rootNode);
            Set<List<Integer>> used = new HashSet<>();
            used.add(List.of(rootNode.l, rootNode.r));
            int remainTime = k;
            long result = 0;

            while (!maxHeap.isEmpty() && remainTime > 0) {
                Node node = maxHeap.poll();
                System.out.printf("val: %s, L: %s, R: %s%n", node.val, node.l, node.r);
                int l = node.l;
                int r = node.r;
                result += node.val;
                // add two subArrays
                if (l + 1 <= r && !used.contains(List.of(l + 1, r))) {
                    int val = tree.queryMax(l + 1, r) - tree.queryMin(l + 1, r);
                    maxHeap.add(new Node(l + 1, r, val));
                    used.add(List.of(l + 1, r));
                }
                if (r - 1 >= l && !used.contains(List.of(l, r - 1))) {
                    int val = tree.queryMax(l, r - 1) - tree.queryMin(l, r - 1);
                    maxHeap.add(new Node(l, r - 1, val));
                    used.add(List.of(l, r - 1));
                }

                remainTime -= 1;
            }

            return result;
        }
    }
}
