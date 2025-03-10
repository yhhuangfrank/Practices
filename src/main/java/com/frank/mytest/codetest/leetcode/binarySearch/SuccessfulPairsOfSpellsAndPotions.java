package com.frank.mytest.codetest.leetcode.binarySearch;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {
//    Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
//    Output: [4,0,3]
//    Explanation:
// - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
//- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
//- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
//  Thus, [4,0,3] is returned.

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.successfulPairs(new int[]{5, 1, 3}, new int[]{5, 4, 3, 2, 1}, 7)));
        System.out.println(Arrays.toString(sol.successfulPairs(new int[]{1, 1, 1}, new int[]{1, 1, 1, 1, 1}, 1)));
        System.out.println(Arrays.toString(sol.successfulPairs(new int[]{1, 1, 1}, new int[]{1, 1, 1, 1, 1}, 2)));
    }

    static class Solution {
        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            int n = spells.length;
            int m = potions.length;
            int[] res = new int[n];
            int[] p = Arrays.copyOf(potions, m);
            Arrays.sort(p);
            for (int i = 0; i < n; i++) {
                int spell = spells[i];
                long maxP =(long) p[m - 1] * spell;
                if (maxP < success) continue; // 最大的乘積也不符合，res[i] = 0, 不往下進行binary search
                // binary search 找到最大的 idx 使得乘積剛好小於 success
                int l = 0;
                int r = m - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    long product = (long) spell * p[mid];
                    if (product >= success) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                res[i] += m - l;
            }
            return res;
        }
    }
}
