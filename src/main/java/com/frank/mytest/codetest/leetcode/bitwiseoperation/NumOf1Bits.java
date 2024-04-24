package com.frank.mytest.codetest.leetcode.bitwiseoperation;

public class NumOf1Bits {
    public static void main(String[] args) {
        // 給定一正整數，判斷在二進制表示法內共有多少個 1
        System.out.println(hammingWeight(11));
    }
    public static int hammingWeight(int n) {
        int count = 0;
        int temp = n;
        while (temp > 0) {
            int val = temp & 1;
            if (val == 1) {
                count++;
            }
            temp = temp >> 1;
        }
        return count;
    }
}
