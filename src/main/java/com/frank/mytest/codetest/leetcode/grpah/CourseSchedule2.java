package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class CourseSchedule2 {
    public static void main(String[] args) {
        CourseSchedule2 courseSchedule2 = new CourseSchedule2();
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        System.out.println(Arrays.toString(courseSchedule2.findOrder(numCourses, prerequisites))); // [0,1,2,3] or [0,2,1,3]
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[0]).add(prerequisite[1]);
        }
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        Set<Integer> path = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adj, visited, path, res)) {
                return new int[0];
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int cur, Map<Integer, List<Integer>> adj, boolean[] visited, Set<Integer> path, List<Integer> res) {
        if (path.contains(cur)) {
            return false;
        }
        if (visited[cur]) {
            return true;
        }
        visited[cur] = true;
        path.add(cur);
        for (int neigh : adj.get(cur)) {
            if (!dfs(neigh, adj, visited, path, res)) {
                return false;
            }
        }
        path.remove(cur);
        res.add(cur);
        return true;
    }
}
