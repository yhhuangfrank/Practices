package com.frank.mytest.codetest.dsa.tree;


/**
 * 使用 Node 方式建立
 */
public class SegmentTreeDemo2 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        SegmentTree segmentTree = new SegmentTree(nums);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 4));
        segmentTree.update(3, 0);
        System.out.println(segmentTree.query(2, 4));
    }


    private static class SegmentTree {
        private final Node root;

        public SegmentTree(int[] nums) {
            this.root = this.build(nums, 0, nums.length - 1);
        }

        private Node build(int[] nums, int l, int r) {
            if (l == r) {
                return new Node(nums[l], l, r);
            }
            Node node = new Node(0, l, r);
            int m = (l + r) / 2;
            node.left = this.build(nums, l, m);
            node.right = this.build(nums, m + 1, r);
            node.sum = node.left.sum + node.right.sum;
            return node;
        }

        public void update(int index, int val) {
            updateHelper(this.root, index, val);
        }

        private void updateHelper(Node root, int index, int val) {
            if (root.L == root.R && root.L == index) {
                root.sum = val;
                return;
            }
            int m = (root.L + root.R) / 2;
            if (index > m) {
                this.updateHelper(root.right, index, val);
            } else {
                this.updateHelper(root.left, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        public int query(int l, int r) {
            return queryHelper(this.root, l, r);
        }

        private int queryHelper(Node root, int l, int r) {
            if (root.L > r || l > root.R) return 0; // edge case
            if (root.L == l && root.R == r) {
                return root.sum;
            }
            int m = (root.L + root.R) / 2;
            if (l > m) {
                return queryHelper(root.right, l, r);
            } else if (r <= m) {
                return queryHelper(root.left, l, r);
            } else {
                return queryHelper(root.left, l, m) + queryHelper(root.right, m + 1, r);
            }
        }

        // 儲存資訊
        private static class Node {
            int sum;
            int L; // lowerBound
            int R; // upperBound
            Node left;
            Node right;

            public Node(int sum, int L, int R) {
                this.sum = sum;
                this.L = L;
                this.R = R;
            }
        }
    }
}
