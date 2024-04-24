package com.frank.mytest.codetest.dsa.algo.divideandconquer;

public class HanoiTower {
    public static void main(String[] args) {

        hanoiTower(2, "A", "B", "C");
    }

    // 河內塔的移動方法，使用分治算法
    public static void hanoiTower(int num, String a, String b, String c) {
        // 如果只有一個盤
        if (num == 1) {
            System.out.printf("從 %s -> %s%n", a, c);
        } else {
            // 把 n -1 個盤從 a -> b ，中間使用 c
            hanoiTower((num - 1), a, c, b);
            System.out.printf("從 %s -> %s%n", a, c);
            // 把 n -1 個盤從 b -> c ，中間使用 a
            hanoiTower((num - 1), b, a, c);
        }
    }
}
