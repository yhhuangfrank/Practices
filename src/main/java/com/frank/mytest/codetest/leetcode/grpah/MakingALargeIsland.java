package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class MakingALargeIsland {

    public static void main(String[] args) {
        Solution s = new Solution();

    }

    static class Solution {
        int[][] direction = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int n;
        int id = 2;
        Map<Integer, Integer> idToArea = new HashMap<>();

        public int largestIsland(int[][] grid) {
            n = grid.length;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] != 1) continue;
                    int area = dfs(r, c, grid, id);
                    idToArea.put(id, area);
                    id++;
                }
            }
            // 對於每個 0，計算周遭相鄰島嶼面積和 + 1，
            // 其中最大值即為答案
            int maxArea = 0;
            boolean isZeroNotExist = true;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 0) {
                        isZeroNotExist = false;
                        int area = 1;
                        Set<Integer> neighborIslandIds = new HashSet<>();
                        for (int[] d : direction) {
                            int nr = r + d[0];
                            int nc = c + d[1];
                            if (
                                    nr < 0 || nc < 0 || nr == n || nc == n || grid[nr][nc] == 0
                            ) {
                                continue;
                            }
                            neighborIslandIds.add(grid[nr][nc]);
                        }
                        for (int neighborId : neighborIslandIds) {
                            area += idToArea.get(neighborId);
                        }
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }

            return isZeroNotExist ? n * n : maxArea;
        }

        private int dfs(int r, int c, int[][] grid, int id) {
            int area = 1;
            grid[r][c] = id;
            for (int[] d : direction) {
                int dr = d[0];
                int dc = d[1];
                int nr = r + dr;
                int nc = c + dc;
                if (nr < 0 || nc < 0 || nr == n || nc == n || grid[nr][nc] == id || grid[nr][nc] == 0) {
                    continue;
                }
                area += dfs(nr, nc, grid, id);
            }
            return area;
        }
    }
}
