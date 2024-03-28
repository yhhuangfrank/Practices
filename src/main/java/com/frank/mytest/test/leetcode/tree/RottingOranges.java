package com.frank.mytest.test.leetcode.tree;

import java.util.*;

/**
 * 給定一個二維方格，每一格子可能有三個值
 * 0: 此格為空
 * 1: 有一顆新鮮橘子
 * 2: 有一顆腐爛橘子
 * <p>
 * 每經過一分鐘，腐爛橘子的四個方向格子會被感染。問最少經過多久會都沒有新鮮橘子
 */
public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
//        System.out.println(orangesRotting(grid));
//        System.out.println(orangesRotting(grid2));
        System.out.println(orangeRottingV2(grid));
        System.out.println(orangeRottingV2(grid2));
    }

    public static int orangesRotting(int[][] grid) {
        if (grid.length == 0) return 0;
        boolean isRottenExist = false;
        int freshCount = 0;

        Deque<Integer[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    isRottenExist = true;
                    queue.add(new Integer[]{i, j});
                }
            }
        }
        if (freshCount == 0) return 0; // 一開始就沒有新鮮橘子
        if (!isRottenExist) return -1; // 一開始有新鮮橘子，但沒有腐爛橘子

        int minutes = 0;
        while (!queue.isEmpty()) {
            minutes++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer[] ints = queue.removeFirst();
                int x = ints[0];
                int y = ints[1];
                freshCount = calNewFreshCount(grid, x, y, freshCount, queue);
            }
            if (freshCount == 0)
                break;
        }

        if (freshCount > 0) return -1; // 表示有新鮮橘子的位置是腐爛橘子都碰不到的
        return minutes;
    }

    private static int calNewFreshCount(int[][] grid, int x, int y, int freshCount, Deque<Integer[]> queue) {
        int result = freshCount;
        if (x - 1 >= 0 && grid[x - 1][y] == 1) { // up
            result--;
            grid[x - 1][y] = 2;
            queue.add(new Integer[]{x - 1, y});
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 1) { // left
            result--;
            grid[x][y - 1] = 2;
            queue.add(new Integer[]{x, y - 1});
        }
        if (x + 1 < grid.length && grid[x + 1][y] == 1) { // down
            result--;
            grid[x + 1][y] = 2;
            queue.add(new Integer[]{x + 1, y});
        }
        if (y + 1 < grid[0].length && grid[x][y + 1] == 1) { // right
            result--;
            grid[x][y + 1] = 2;
            queue.add(new Integer[]{x, y + 1});
        }
        return result;
    }


    // 第二種寫法，從好橘子思考
    public static int orangeRottingV2(int[][] grid) {
        // 紀錄所有好橘子所在位置
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    list.add(new Integer[]{i, j});
                }
            }
        }
        int timeElapsed = 0;
        while (true) {
            List<Integer[]> newList = new ArrayList<>();
            List<Integer[]> newRotten = new ArrayList<>();
            for (Integer[] ints : list) {
                boolean willRotten = checkRotten(ints, grid);
                if (willRotten) {
                    newRotten.add(ints);
                } else {
                    newList.add(ints);
                }
            }
            if (newList.size() == list.size()) break; // 好橘子都沒有改變
            timeElapsed++;
            list = newList;
            updateNewRotten(newRotten, grid);
        }

        if (!list.isEmpty()) return -1; // 仍然有好橘子
        return timeElapsed;
    }

    private static void updateNewRotten(List<Integer[]> newRotten, int[][] grid) {
        for (Integer[] ints : newRotten) {
            int x = ints[0];
            int y = ints[1];
            grid[x][y] = 2;
        }
    }

    private static boolean checkRotten(Integer[] ints, int[][] grid) {
        int x = ints[0];
        int y = ints[1];
        if (x - 1 >= 0 && grid[x - 1][y] == 2) return true;
        if (y - 1 >= 0 && grid[x][y - 1] == 2) return true;
        if (x + 1 < grid.length && grid[x + 1][y] == 2) return true;
        return y + 1 < grid[0].length && grid[x][y + 1] == 2;
    }
}
