package com.frank.mytest.codetest.leetcode.grpah;


public class IslandPerimeter {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.islandPerimeter(new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}})); // 16
        System.out.println(solution.islandPerimeter(new int[][]{{1}})); // 4
        System.out.println(solution.islandPerimeter(new int[][]{{1, 0}})); // 4
    }

    static class Solution {
        // O(n * m) time
        public int islandPerimeter(int[][] grid) {
            boolean[][] visit = new boolean[grid.length][grid[0].length];
            int res = 0;
            boolean finish = false;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) {
                        res += dfs(r, c, grid, visit);
                        finish = true;
                        break;
                    }
                }
                if (finish) break;
            }
            return res;
        }

        private int dfs(int r, int c, int[][] grid, boolean[][] visit) {
            if (Math.min(r, c) < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0) return 1;
            if (visit[r][c]) return 0;
            visit[r][c] = true;
            int res = 0;
            res += dfs(r + 1, c, grid, visit);
            res += dfs(r, c + 1, grid, visit);
            res += dfs(r - 1, c, grid, visit);
            res += dfs(r, c - 1, grid, visit);
            return res;
        }
    }
}
