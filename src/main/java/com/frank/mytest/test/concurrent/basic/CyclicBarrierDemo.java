package com.frank.mytest.test.concurrent.basic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    static int DRAGONBALL_NUM = 7;

    public static void main(String[] args) {

        // 集齊7顆龍珠後，召喚神龍
        CyclicBarrier cyclicBarrier = new CyclicBarrier(DRAGONBALL_NUM, () -> System.out.println("收集到 7 顆龍珠了，召喚神龍！！！"));

        // 蒐集龍珠過程
        for (int i = 1; i <= DRAGONBALL_NUM; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 號龍珠收集到了！");
                    cyclicBarrier.await(); // 當龍珠沒有蒐集到 7 顆，持續等待
                    System.out.println("任務結束...");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }

    }
}
