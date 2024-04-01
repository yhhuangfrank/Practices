package com.frank.mytest.test.leetcode.heap;

import java.util.PriorityQueue;

/**
 * 假定有一正整數數列，數字代表石頭重量，每次取兩個最重的石頭出來 (x,y 其中 x >= y)，互相撞擊
 * 1) 若 x == y, 兩個石頭毀壞
 * 2) 若 x > y, x 重量剩下 x - y
 * 問最終若有剩下一個石頭，它的重量為何？ 若沒有任何剩下則回傳 0
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 4, 1, 8, 1};
        int[] arr1 = new int[]{1};
        System.out.println(lastStoneWeight(arr));
        System.out.println(lastStoneWeight(arr1));
    }

    public static int lastStoneWeight(int[] stones) {
        if (stones.length == 0) return 0;
        if (stones.length == 1) return stones[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 預設是 min heap
        for (int s : stones) {
            pq.add(-s); // 加入、取出時加上負號，可當作為 max heap
        }

        while (pq.size() > 1) {
            int x = -pq.remove();
            int y = -pq.remove();
            if (x == y) continue;
            x = x - y;
            pq.add(-x);
        }
        if (pq.isEmpty()) return 0;
        return -pq.remove();
    }
}
