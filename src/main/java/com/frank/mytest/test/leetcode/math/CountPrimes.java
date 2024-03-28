package com.frank.mytest.test.leetcode.math;

import java.util.Arrays;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 * Example 1:
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * Example 2:
 * Input: n = 0
 * Output: 0
 *
 * Example 3:
 * Input: n = 1
 * Output: 0
 */
public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    public static int countPrimes(int n) { // 不包含 ｎ
        if (n < 3) return 0;
        boolean[] isPrime = new boolean[n]; // n 個整數
        Arrays.fill(isPrime, true);
        int count = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = (int) Math.pow(i,2); j < n; j += i) { // 從平方開始，依照倍數標記為 false
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i < isPrime.length; i++) { // index 0, 1 不算
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

}
