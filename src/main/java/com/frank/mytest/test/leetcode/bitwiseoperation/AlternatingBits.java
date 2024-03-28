package com.frank.mytest.test.leetcode.bitwiseoperation;

/**
 * 判斷一個正整數在二進位表示法後，0 與 1 是否剛好錯開呈現 010101... 或者 101010...
 */
public class AlternatingBits {
    public static void main(String[] args) {
        int n = 7;
        boolean result = hasAlternatingBits(n);
        System.out.println(result);
    }

    /**
     * 想法：若一個符合條件的數經過 ”位元右移“ 後
     * 010101 -> 101010
     * 101010 -> 010101
     * 此時對右移後的數與原先數進行 XOR 運算會得到 111111
     * 將結果 +1 -> 進位後與111111進行 & 運算後會得到 0
     */
    private static boolean hasAlternatingBits(int n) {
        // n                                0 1 0 1 0
        // n >> 1                           0 0 1 0 1
        // n XOR (n >> 1)                   0 1 1 1 1
        // +1                               1 0 0 0 0 變五位 digit
        // 與n XOR (n >> 1) 進行 & 運算      0 0 0 0 0
        int temp = n >> 1;
        temp = n ^ temp;
        return ((temp + 1) & temp) == 0;
    }
}
