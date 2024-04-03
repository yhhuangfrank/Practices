package com.frank.mytest.test.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 給定兩個正整數陣列，兩個相同的數值可以連成一線，每個數只能連線一次，並且線不能交錯
 * 問最多有幾條線
 * <p>
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 * <p>
 * 問題可視為盡可能找到最多的連續相同數 (類似 Longest Common Subsequence, LCS)
 * [2,5,1,2,5]
 * [10,5,2,1,5,2]
 * -> 5 2 5
 * -> 5 2 5
 * <p>
 * [1,4,2,2]
 * [1,2,4,2]
 * -> 1, 2, 2
 */
public class UncrossedLines {

    static Map<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 5, 1, 2, 5};
        int[] nums2 = new int[]{10, 5, 2, 1, 5, 2};
        System.out.println(maxUncrossedLines(nums1, nums2));
    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        return improvedDPSolution(nums1, nums2);
    }


    /**
     * 暴力解 ： 每組對應，找到後不斷往下遞迴，最差狀況可能需要每次對應都開分支，因此 O(2^(m+n)) m,n 為陣列長度
     * 遞迴方式帶入 i, j 代表兩個指針，每次遞迴就處理一組數字
     * 1) 當數字對應到時：
     * -> 1 + 遞迴後續可能結果 1 + dfs(i+1,j+1)
     * 2) 當沒有對應到時：會產生兩個分支，並且要取最大的結果
     * -> Max(dfs(i+1,j) + dfs(i,j))
     */
    public static int dfs(int i, int j, int[] nums1, int[] nums2) {
        if (i == nums1.length || j == nums2.length) {
            return 0; // 超出範圍，匹配不到
        }
        if (nums1[i] == nums2[j]) {
            return 1 + dfs(i + 1, j + 1, nums1, nums2);
        } else {
            return Math.max(dfs(i + 1, j, nums1, nums2), dfs(i, j + 1, nums1, nums2));
        }
    }

    /**
     * 暴力解改良：使用 HashMap 儲存已經做過的組合
     * 例如 i = 1, j = 2 與 i = 2, j = 1 兩種組合依序往後遞迴都會遇到 i = 2, j = 2 情形
     * 並且重複算了一遍，因此可使用 map 紀錄重複已經運算過的結果
     */
    public static int improvedDFS(int i, int j, int[] nums1, int[] nums2) {
        if (i == nums1.length || j == nums2.length) {
            return 0; // 超出範圍，匹配不到
        }
        String key = String.join(",", String.valueOf(i), String.valueOf(j));
        if (cache.get(key) != null) {
            return cache.get(key);
        }
        int value;
        if (nums1[i] == nums2[j]) {
            value = 1 + dfs(i + 1, j + 1, nums1, nums2);
            cache.put(key, value);
        } else {
            value = Math.max(dfs(i + 1, j, nums1, nums2), dfs(i, j + 1, nums1, nums2));
            cache.put(key, value);
        }
        return value;
    }

    /**
     * j ->
     * 1 2 2 4
     * i 1 1 1 1 1
     * | 4 1 1 1 2
     * 2 1 2 2 2
     * 2 1 2 3 3
     * 比對相同值: 1 組配對 + 前面的數列
     * ex : 2 (2, 0) 比對到 2 (0,2)，此時數列為 122, 142，2 與 2 一組 + 12, 14 的結果
     * 比對不同值： 比較前面數列的最大結果
     * ex 2 (3,0) 比對到 4 (0,3)，此時數列為 1224, 1422
     * 找 1224, 142 與 122, 1422 哪個算出來的結果大
     */
    public static int dpSolution(int[] nums1, int[] nums2) {
        int m = nums1.length + 1;
        int n = nums2.length + 1;
        int[][] dp = new int[m][n];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 由於每次只會操作 dp 兩列，因此可以用兩列 array 輪流替換，節省空間
    public static int improvedDPSolution(int[] nums1, int[] nums2) {
        int rows = nums1.length + 1;
        int cols = nums2.length + 1; // 當作 cols

        int[] prev = new int[cols];

        for (int i = 1; i < rows; i++) {
            int[] dp = new int[cols];
            for (int j = 1; j < cols; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = 1 + prev[j-1];
                } else {
                    dp[j] = Math.max(prev[j], dp[j - 1]);
                }
            }
            prev = dp;
        }
        return prev[cols - 1];
    }
}
