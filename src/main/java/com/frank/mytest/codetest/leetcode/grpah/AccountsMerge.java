package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class AccountsMerge {

//    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> accounts = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),
                List.of("John","johnnybravo@mail.com")
        );
        solution.accountsMerge(accounts).forEach(System.out::println);
    }

    static class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
                this.ranks[i] = 0;
            }
        }

        public void union(int node1, int node2) {
            int p1 = this.find(node1);
            int p2 = this.find(node2);
            if (p1 == p2) {
                return;
            }
            int r1 = this.ranks[p1];
            int r2 = this.ranks[p2];
            if (r1 >= r2) {
                this.parents[p2] = p1;
                this.ranks[p1] = this.ranks[p1] + r2;
            } else {
                this.parents[p1] = p2;
                this.ranks[p2] = this.ranks[p2] + r1;
            }
        }

        public int find(int node) {
            int p = this.parents[node];
            while (p != this.parents[p]) {
                this.parents[p] = this.parents[this.parents[p]];
                p = this.parents[p];
            }
            return p;
        }
    }

    static class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            Map<String, Integer> emailToAccId = new HashMap<>();
            UnionFind uf = new UnionFind(n);

            for (int i = 0; i < n; i++) {
                List<String> acc = accounts.get(i);
                for (int j = 1; j < acc.size(); j++) {
                    String email = acc.get(j);
                    if (emailToAccId.containsKey(email)) {
                        uf.union(emailToAccId.get(email), i); // duplicate email should be merged
                    } else {
                        emailToAccId.put(email, i);
                    }
                }
            }

            Map<Integer, List<String>> accIdToEmails = new HashMap<>();
            emailToAccId.forEach((email, accId) -> {
                int parentId = uf.find(accId);
                accIdToEmails.putIfAbsent(parentId, new ArrayList<>());
                accIdToEmails.get(parentId).add(email);
            });

            List<List<String>> res = new ArrayList<>();
            accIdToEmails.forEach((accId, emails) -> {
                List<String> lst = new ArrayList<>();
                lst.add(accounts.get(accId).get(0));
                lst.addAll(emails.stream().sorted().toList());
                res.add(lst);
            });

            return res;
        }
    }
}
