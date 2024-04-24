package com.frank.mytest.codetest.leetcode.binarySearch;

/**
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * <p>
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * <p>
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 */
public class KokoEatingBananas {
    /**
     * 給定一 array 代表多串香蕉, arr[i] 為每串香蕉根數
     * Koko 每次只能拿一串，並可以吃 k 根/小時，若 arr[i] < k 則依舊會消耗一小時。
     * 問在 h 小時內，最慢 k 需為多少才能把所有香蕉吃完
     */
    public static void main(String[] args) {
        int[] piles = new int[]{30, 11, 23, 4, 20};
        System.out.println(minEatingSpeed(piles, 5));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for (int n : piles) {
            max = Math.max(max, n);
        }
        int l = 1;
        int r = max; // 最小速度必定在 1 ... max 之間
        int minSpeed = Integer.MAX_VALUE;
        while (l <= r) {
            long currHour = 0; // 需要時間太長可能導致整數 overflow，因此用 long
            int mid = l + (r - l) / 2; // 取中心點的速度並計算總共需多少時間
            for (int p : piles) {
                // ceil 運算需為 double
                long time = (long) Math.ceil((double) p / (double) mid);
                currHour += time;
            }
            if (currHour <= h) { // 在時限內都算可以的結果
                minSpeed = Math.min(minSpeed, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return minSpeed;
    }
}
