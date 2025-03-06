package com.frank.mytest.codetest.leetcode.grpah;

import java.util.*;

public class NearestExitFromEntranceInMaze {
//    Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
//    Output: 1
//    Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
//    Initially, you are at the entrance cell [1,2].
//            - You can reach [1,0] by moving 2 steps left.
//            - You can reach [0,2] by moving 1 step up.
//    It is impossible to reach [2,3] from the entrance.
//    Thus, the nearest exit is [0,2], which is 1 step away.

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] maze = new char[][]{{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        int[] entrance = new int[]{1, 2};
        System.out.println(sol.nearestExit(maze, entrance)); // 1
    }

    static class Solution {
        //        BFS
        public int nearestExit(char[][] maze, int[] entrance) {
            int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            int ROWS = maze.length;
            int COLS = maze[0].length;
            int step = 0;
            Deque<int[]> queue = new ArrayDeque<>();
            boolean[][] visit = new boolean[ROWS][COLS];
            queue.addLast(entrance);
            visit[entrance[0]][entrance[1]] = true;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cell = queue.pollFirst();
                    int r = cell[0];
                    int c = cell[1];
                    boolean onEdge = r == ROWS - 1 || r == 0 || c == 0 || c == COLS - 1;
                    boolean isNotEntrance = !(r == entrance[0] && c == entrance[1]);
                    if (onEdge && isNotEntrance)
                        return step;
                    for (int[] d : directions) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (Math.min(nr, nc) < 0 || nr == ROWS || nc == COLS || visit[nr][nc] || maze[nr][nc] == '+')
                            continue;
                        visit[nr][nc] = true;
                        queue.addLast(new int[]{nr, nc});
                    }
                }
                step += 1;
            }
            return -1;
        }
    }
}
