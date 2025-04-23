package com.frank.mytest.codetest.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        root.right = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        Codec codec = new Codec();
        String serialized = codec.serialize(root);
        System.out.println(serialized); // 1,2,3,N,N,4,5,N,N,N,N,:1,2,4,4
        TreeNode deserialize = codec.deserialize(serialized);
        System.out.println(deserialize); // 1
        System.out.println(deserialize.left); // 2
        System.out.println(deserialize.right); // 3
        System.out.println(deserialize.right.left); // 4
        System.out.println(deserialize.right.right); // 5
    }

    static class Codec {

        // Encodes a tree to a single string.
        // By BFS traversal and record levelCount
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder res = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            List<String> levelCounts = new ArrayList<>();
            while (!queue.isEmpty()) {
                int size = queue.size();
                levelCounts.add(String.valueOf(size));
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.pollFirst();
                    String val = node == null ? "N" : String.valueOf(node.val);
                    res.append(val);
                    res.append(",");
                    if (node != null) {
                        queue.addLast(node.left);
                        queue.addLast(node.right);
                    }
                }
            }
            res.append(":");
            res.append(String.join(",", levelCounts));
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] arr = data.split(":");
            String[] nodes = arr[0].substring(0, arr[0].length() - 1).split(",");
            String[] levelCounts = arr[1].split(",");
            LinkedList<TreeNode> prev = new LinkedList<>();
            LinkedList<TreeNode> cur = new LinkedList<>();
            int i = nodes.length - 1;
            int j = levelCounts.length - 1;
            while (i >= 0) {
                int end = i - Integer.parseInt(levelCounts[j]);
                while (i >= 0 && i > end) {
                    TreeNode node = "N".equals(nodes[i]) ? null : new TreeNode(Integer.parseInt(nodes[i]));
                    cur.addLast(node);
                    i--;
                }
                if (prev.isEmpty()) {
                    while (!cur.isEmpty()) {
                        prev.addLast(cur.pollFirst());
                    }
                    continue;
                }
                int size = cur.size();
                for (int k = 0; k < size; k++) {
                    TreeNode root = cur.pollFirst();
                    if (root != null) {
                        TreeNode right = prev.pollFirst();
                        TreeNode left = prev.pollFirst();
                        root.right = right;
                        root.left = left;
                    }
                    prev.addLast(root);
                }
                j--;
            }
            return prev.pollFirst();
        }
    }
}


