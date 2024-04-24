package com.frank.mytest.codetest.leetcode.dp;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 * <p>
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 * <p>
 * Return the minimum number of flips to make s monotone increasing.
 * Example 1:
 * <p>
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 * <p>
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 * <p>
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 */
public class MonotoneIncreasing {
    public static void main(String[] args) {
        System.out.println(flipToMonotoneIncreasing("00110"));
        System.out.println(flipToMonotoneIncreasing("010110"));
        System.out.println(flipToMonotoneIncreasing("00011000"));
    }

    /**
     * 整理邏輯
     * 單調遞增：由左至右到某個點變為 1 時，後續通通都要是 1
     * 最後情況可能有兩種
     * 1) 全部都是 0
     * 2) 前面是 0, 接下來後面都是 1
     * <p>
     * 在某位置 i 時要保持單調遞增
     * 1) s[i] == 0, 狀況 1 -> 不需動作
     * 2) s[i] == 1, 狀況 1 -> 需翻轉成 0
     * 3) s[i] == 0, 狀況 2 -> 需翻轉成 1
     * 4) s[i] == 1, 狀況 2 -> 第 i-1 位置是狀況 1 或 2 都可以
     * <p>
     * 假設到前一步為止走狀況一所需翻轉次數為 f1, 狀況二的翻轉次數為 f2
     * 從 i - 1 位到 i 位時的 f1, f2:
     * 對 1,3 情況 -> f1 不需增加，f2 需遞增
     * 對 2,4 情況 -> f1 需遞增，f2 則是需要考慮 i-1 的次數 (假設寫為 f1(i-1), f2(i-1)) 。因在此狀況 i-1 是狀況1或2 皆可，因此需取步數少的 (最小值)
     * <p>
     * 因此：
     * 一開始 f1, f2 設為 0 (兩種結果的步數初始值)
     * 一路往右走，算出最後兩結果的總步數，再找比較小的那個即是答案
     */
    public static int flipToMonotoneIncreasing(String s) {
        int f1 = 0;
        int f2 = 0;

        for (int i = 0; i < s.length(); i++) {
            if ('0' == s.charAt(i)) { // 值為 0
                f2++;
            } else { // 值為 1
                f2 = Math.min(f1,f2); // 考慮前一位置的最小步數
                f1++; // f1 情況
            }
        }
        return Math.min(f1, f2);
    }
}
