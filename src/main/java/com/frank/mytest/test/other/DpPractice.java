package com.frank.mytest.test.other;

import java.util.Arrays;

/**
 * 草莓冰2元
 * 檸檬冰5元
 * 巧克力冰10元
 * 牛奶冰20元
 * 你有N元，希望「正好花完」，最少要買多少杯冰?
 */
public class DpPractice {

    static int[][] dp = new int[5][25];
    static int[] prices = new int[] {2, 5, 10, 20};

    static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        int n = 21;
        System.out.println(buyIceCream(0, n));
//        System.out.println(count);

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
    }

    /**
     * 0 杯草莓冰 -> 剩下 21 買 其他三種，算出後加上 0 杯草莓冰
     * 1 杯草莓冰 -> 剩下 19 買 其他三種，算出後加上 1 杯草莓冰
     * ...
     * 10 杯草莓冰 -> 剩下 1 買 其他三種，算出後加上 10 杯草莓冰
     */
    public static int buyIceCream(int index, int remain) { // 每次買時，考慮是第幾個物品，以及剩餘多少錢
        count++;
        if (remain == 0) return 0; // 表示再買 0 杯就可花完
        if (index > 3) return -1; // 所有冰都考慮完了。表示沒有這種杯數

        if (dp[index][remain] != -1) return dp[index][remain];

        int ans = 1000000;
        for (int i = 0; i * prices[index] <= remain; i++) {
            int t = buyIceCream(index + 1, remain - i * prices[index]); // 考慮剩下的選項是否可把錢花完並回傳最少杯數
            if (t > -1 && (t + i < ans)) { // 是否為總杯數更少的買法
                System.out.println("t " + t + ", remain: " + remain);
                ans = t + i;
                System.out.println(ans + ", " + i + ", index : " + index);
            }
        }
        dp[index][remain] = ans;
        return ans;
    }
}
