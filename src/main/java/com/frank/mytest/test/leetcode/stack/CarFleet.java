package com.frank.mytest.test.leetcode.stack;

import java.util.*;

public class CarFleet {
    public static void main(String[] args) {
        int target = 12;
        int[] position = new int[]{10, 8, 0, 5, 3};
        int[] speed = new int[]{2, 4, 1, 1, 3};
        System.out.println(carFleet(target, position, speed));
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        // 單行道不能超車因此會被前一台車卡住，位置進行排序後，計算到終點所需時間，若比較遠的位置所需時間較前一台車少，那麼此兩台車會形成一車隊
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            double positionI = position[i];
            double speedI = speed[i];
            double time = (target - positionI) / speedI; // 到終點所需時間
            Pair p = new Pair(positionI, speedI, time);
            pairs.add(p);
        }
        pairs.sort((p1, p2) -> (int) p1.position - (int) p2.position); // O(nlogn)
        // 用一 stack 從離 target 最近的位置開始
        Deque<Pair> stack = new ArrayDeque<>();
        for (int i = pairs.size() - 1; i >= 0; i--) {
            Pair p = pairs.get(i);
            if (stack.isEmpty()) {
                stack.push(p);
                continue;
            }
            if (pairs.get(i).time > stack.peek().time) {
                stack.push(p);
            }
        }
        return stack.size();
    }

    private static class Pair {
        double position;
        double speed;
        double time;

        public Pair(double position, double speed, double time) {
            this.position = position;
            this.speed = speed;
            this.time = time;
        }
    }
}
