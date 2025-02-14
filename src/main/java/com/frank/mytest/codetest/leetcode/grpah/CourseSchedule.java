package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();
        System.out.println(solution.canFinish(3, new int[][]{{0, 1}, {1, 0}})); // false
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[0]).add(prerequisite[1]);
        }

        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> path = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adj, visited, path)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int cur, Map<Integer, List<Integer>> adj, Set<Integer> visited, Set<Integer> path) {
        if (path.contains(cur)) {
            return false;
        }
        if (visited.contains(cur)) {
            return true;
        }
        visited.add(cur);
        path.add(cur);
        for (int neighbor : adj.get(cur)) {
            if (!dfs(neighbor, adj, visited, path)) return false;
        }
        path.remove(cur);
        return true;
    }
}
