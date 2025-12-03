package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

/**
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 */
public class MakingALargeIsland {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.largestIsland(new int[][]{{1, 0}, {0, 1}})); // 3
        System.out.println(s.largestIsland(new int[][]{{1, 1}, {1, 0}})); // 4
        System.out.println(s.largestIsland(new int[][]{{1, 1}, {1, 1}})); // 4
    }

    static class Solution {
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int n;
        int id = 2;
        Map<Integer, Integer> idToArea = new HashMap<>();

        public int largestIsland(int[][] grid) {
            n = grid.length;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    // 為每個島嶼標記一個 id，並 cache
                    if (grid[r][c] != 1) continue;
                    int area = dfs(r, c, grid, id);
                    idToArea.put(id, area);
                    id++;
                }
            }
            // 對於每個 0，計算周遭相鄰島嶼面積和 + 1，
            // 其中最大值即為答案
            int maxArea = 0;
            boolean isZeroNotExist = true; // 特殊情況，沒有 0
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
            // 是否沒有水 (0)
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
                // 排除已經標記id的位置與位置是水 (= 0)
                if (nr < 0 || nc < 0 || nr == n || nc == n || grid[nr][nc] == id || grid[nr][nc] == 0) {
                    continue;
                }
                area += dfs(nr, nc, grid, id);
            }
            return area;
        }
    }
}
