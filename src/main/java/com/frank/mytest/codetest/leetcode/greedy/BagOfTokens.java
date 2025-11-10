package com.frank.mytest.codetest.leetcode.greedy;

import java.util.Arrays;

/**
 * Input: tokens = [100,200,300,400], power = 200
 * Output: 2
 * Input: tokens = [100], power = 50
 * Output: 0
 * Input: tokens = [200,100], power = 150
 * Output: 1
 */
public class BagOfTokens {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        System.out.println(solution.bagOfTokensScore(new int[]{100}, 50));
        System.out.println(solution.bagOfTokensScore(new int[]{200, 100}, 150));
    }

    static class Solution {
        /**
         * score 從 0 開始
         * 規則:
         * 一次只能選一種操作，直到無法繼續
         * 1. 若 power 大於某個 tokens[i]，從 power 扣除 tokens[i]，score + 1
         * 2. 若 score > 0，增加某個 tokens[i]，power 增加 tokens[i]，score - 1
         * 最大化分數方法
         * - 盡可能用小 token 增加 score，用大 token 增加 power，因此需從小到大排序
         */
        public int bagOfTokensScore(int[] tokens, int power) {
            Arrays.sort(tokens);
            int l = 0;
            int r = tokens.length - 1;

            int curPower = power;
            int score = 0;
            int maxScore = 0;

            while (l <= r) {
                if (curPower >= tokens[l]) {
                    score++;
                    maxScore = Math.max(maxScore, score);
                    curPower -= tokens[l];
                    l++;
                } else if (score > 0) {
                    score--;
                    curPower += tokens[r];
                    r--;
                } else {
                    break;
                }
            }

            return maxScore;
        }
    }

}
