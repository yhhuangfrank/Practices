package com.frank.mytest.codetest.leetcode.array.slidingwindow;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = {7, 6, 4, 3, 1, 6};
        System.out.println(maxProfit(prices));
        String a = "%#A";
        System.out.println(a.codePointAt(0));
        System.out.println(a.codePointAt(1));
        System.out.println(a.codePointAt(2));
    }

    public static int maxProfit(int[] prices) {
        // two pointers, time complexity O(n)
        int maxProfit = 0;
        int l = 0; // pointer for the buy day
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prices[l]) { // sell day found & calculate profit
                int profit = prices[i] - prices[l];
                maxProfit = Math.max(maxProfit, profit);
            } else { // update new buy day
                l = i;
            }
        }
        return maxProfit;
    }
}
