package com.frank.mytest.test.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 給定一個 m x n 場地，初始球在 (startRow, startColumn) 上，共有 maxMove 步數可以移動，要在 maxMove 歸零以前將球移出邊界
 * 找到共有幾個移動方法
 * 結果可能很大，需將結果 mod (10^9 + 7)
 */
public class OutOfBoundaryPaths {
    static int maxRow;
    static int maxCol;
    static int M = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) {
        System.out.println(findPaths(2, 2, 2, 0, 0)); // 6
        //System.out.println(findPaths(8, 50, 23, 5, 26)); // 914783380
    }

    /**
     * 使用一個 map 來紀錄 (i,j,N) 所需步數
     * 將 (i,j,N, map) 帶入遞迴計算某位置 (i,j) 剩下多少步的路徑總數
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        maxRow = m;
        maxCol = n;
        Map<String, Long> map = new HashMap<>();
        return (int) (dfs(startRow, startColumn, maxMove, map) % M);
    }

    /**
     * 如果i,j,N 組合已經在 map 中，代表此情形已經計算過
     * 如果 N 還沒歸零：
     * 1. 初始化路徑數 r = 0
     * 2-1 若 i - 1 < 0 (代表在邊界) r 遞增，否則 r 要加上 dfs(i-1, j, N-1, map) 的結果
     * 2-2 處理 i + 1 == m 情形
     * 2-3 處理 j - 1 < 0 情形
     * 2-4 處理 j + 1 == n 情形
     * 3. 算出 r 之後讓 map 加入 ((i,j,N), r) 回傳 r
     * <p>
     * 若 N == 0，代表沒有剩餘步數，回傳 0
     */
    public static long dfs(int i, int j, int N, Map<String, Long> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(",");
        sb.append(j);
        sb.append(",");
        sb.append(N);
        if (map.get(sb.toString()) != null) return map.get(sb.toString());
        if (N == 0) return 0;
        long r = 0;
        // 碰到左邊界
        r += j - 1 < 0 ? 1 : dfs(i, j - 1, N - 1, map) % M;
        // 碰到上邊界
        r += i - 1 < 0 ? 1 : dfs(i - 1, j, N - 1, map) % M;
        // 碰到右邊界
        r += j + 1 == maxCol ? 1 : dfs(i, j + 1, N - 1, map) % M;
        // 碰到下邊界
        r += i + 1 == maxRow ? 1 : dfs(i + 1, j, N - 1, map) % M;
        map.put(sb.toString(), r);
        System.out.println(map);
        return r;
    }
}
