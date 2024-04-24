package com.frank.mytest.codetest.leetcode.bitwiseoperation;

import java.util.Arrays;

/**
 * 給定零或正整數 n，返回一陣列，長度為 n + 1，每項元素為該處 index 的二進位表示中 "1" 的出現次數
 */
public class CountingBits {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBitsByDP(5)));
    }

    // 一般解法 O(nlogn)
    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        int count;
        int temp;
        for (int i = 0; i < res.length; i++) {
            count = 0;
            temp = i;
            while(temp > 0) {
                int val = temp & 1;
                if (val == 1) {
                    count++;
                }
                temp = temp >> 1;
            }
            res[i] = count;
        }
        return res;
    }

    //
    /*
     * 解法二： dp O(n)
     * 0 --> 0  0
     * 1 --> 01 dp[1 - 1] + 1
     * 2 --> 10 dp[2 - 2] + 1
     * 3 --> 11 dp[3 - 2] + 1
     * 4 --> 100 dp[4 - 4] + 1
     * 5 --> 101 dp[5 - 4] + 1
     * 6 --> 110 dp[6 - 4] + 1
     * 7 --> 111 dp[7 - 4] + 1
     * 8 --> 1000 dp[8 - 8] + 1
     * ...
     * n --> xxxx dp[n - offset] + 1
     */
    public static int[] countBitsByDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int offset = 1;
        int prevOffset = 1;
        for (int i = 1; i < dp.length; i++) {
            if (offset == i) {
                dp[i] = dp[i - offset] + 1;
                prevOffset = offset;
                offset = offset << 1;
            } else {
                dp[i] = dp[i - prevOffset] + 1;
            }

        }
        return dp;
    }
}
