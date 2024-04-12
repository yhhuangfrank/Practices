package com.frank.mytest.test.leetcode.bitwiseoperation;

/**
 * 將一個 32 bits 表示的二進位後，算出反轉後的數字
 */
public class RevereBits {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(reverseBits(-1));
        System.out.println(reverseBitsV2(-1));
    }

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));
        sb.reverse();
        while (sb.length() < 32) {
            sb.append("0");
        }
        String s = sb.toString();
        int sum = 0;
        int base = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            String c = s.charAt(i) + "";
            sum += base * Integer.parseInt(c);
            base *= 2;
        }
        return sum;
    }

    public static int reverseBitsV2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (n >> i) & 1; // 取得原有數字的每一位 bit
            res = res | (bit << (31 - i)); // 因反轉，最後一位必須移動到第 32 位，並對目前 res 進行 or 運算
        }
        return res;
    }
}
