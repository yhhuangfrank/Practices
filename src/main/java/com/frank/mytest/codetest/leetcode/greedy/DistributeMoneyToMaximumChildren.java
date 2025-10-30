package com.frank.mytest.codetest.leetcode.greedy;

public class DistributeMoneyToMaximumChildren {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.distMoney(20, 3)); // 1
        System.out.println(solution.distMoney(16, 2)); // 2
        System.out.println(solution.distMoney(50, 2)); // 1
    }

    /**
     * 規則：
     * 1. 錢要發完
     * 2. 每人至少有1元
     * 3. 不能有人拿4元
     * 問最多有幾個小朋友可以拿剛好8元
     */
    static class Solution {
        public int distMoney(int money, int children) {
            if (money < children) return -1; // 錢不夠發給每人1元
            if (money == 8 * children) return children; // 錢剛好每人8元
            int remainMoney = money - children; // 先發每人1元
            int res = Math.min(remainMoney / 7, children); // 看錢能發最多小朋友湊滿8元
            remainMoney -= res * 7;
            int remainChildren = children - res;

            if (remainChildren == 0 && remainMoney > 0) return res - 1; // 錢還有剩，代表其中一位會不符合8元條件
            if (remainMoney == 3 && remainChildren == 1) return res - 1; // 剛好會只剩下一位小朋友湊到4元
            return res;
        }
    }
}
