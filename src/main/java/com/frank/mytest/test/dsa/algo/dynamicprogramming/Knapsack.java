package com.frank.mytest.test.dsa.algo.dynamicprogramming;

import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {

        int[] w = {1, 4, 3}; // 各物品重量
        int[] values = {1500, 3000, 2000}; // 各物品價值
        int m = 4; // 背包的容量
        int n = values.length; // 物品個數

        // 1) 創建二維陣列表示各個背包容量大小下，對應可放物品個數所擁有的最大價值 (每個物品只能放一次)
        int[][] maxValues = new int[n + 1][m + 1];
        // 1-1)初始化 v[i][0] = v[0][j] = 0。沒有背包容量和沒有物品的價值皆為 0 (不特別做預設也是 0)
        for (int i = 0; i < maxValues.length; i++) {
            maxValues[i][0] = 0; // 將第一行設為 0
        }
        Arrays.fill(maxValues[0], 0); // 將第一列設為 0
//        printValues(maxValues);

        // 2) 紀錄目前背包放置物品情況
        int[][] path = new int[n + 1][m + 1];

        // 3) 根據動態規劃處理
        for (int i = 1; i < maxValues.length; i++) { // i 從 1 開始
            for (int j = 1; j < maxValues[0].length; j++) { // j 從 1 開始
                if (w[i - 1] > j) { // 取物品 index 從 0 開始，因此減一
                    maxValues[i][j] = maxValues[i - 1][j];
                } else {
                    int previous = maxValues[i - 1][j]; // 在相同背包容量下，不考慮新物品情況下的最大價值
                    // values[i - 1]，新物品的價值，index 從 0 開始
                    // maxValues[i - 1][j - w[i - 1]] 表示扣除新物品重量後的剩餘容量內其他物品的最大價值
                    int newValue = values[i - 1] + maxValues[i - 1][j - w[i - 1]];
//                    maxValues[i][j] = Math.max(previous, newValue);
                    if (previous < newValue) { // 代表新商品要放入背包中
                        path[i][j] = 1; // 紀錄最優解
                    }
                    maxValues[i][j] = newValue;
                }
            }
        }

        System.out.println("======= dynamic programming ======");
        printMaxValues(maxValues);

        getMaxValueItems(path, w);
    }

    private static void getMaxValueItems(int[][] path, int[] weight) {
        // 當 path 內紀錄為 1，代表遇到需要放入新物品
        // 由後往前遍歷
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第 %d 個物品放入背包%n", i);
                j -= weight[i - 1];
            }
            i--;
        }
    }

    private static void printMaxValues(int[][] maxValues) {
        for (int[] values : maxValues) {
            System.out.println(Arrays.toString(values));
        }
    }
}
