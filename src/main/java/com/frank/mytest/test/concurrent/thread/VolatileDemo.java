package com.frank.mytest.test.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    /**
     * 使用：把 value 定義為 volatile 變量，由於 setter 對變量的修改不依賴於 value 原值，滿足 volatile 使用場景
     * 理由：利用 volatile 保證數據讀取操作的可見性，利用 synchronized 保證複合操作的原子性，結合使用鎖和 volatile 變量來減少同步的開銷
     */
    private volatile int value = 1;

    public int getValue() {
        return value; // 利用 volatile 保證數據讀取操作的可見性
    }

    public synchronized void setValue() {
        ++value; // 利用 synchronized 保證複合操作的原子性
    }

    // 展示 volatile 變量的可見性
    static volatile boolean flag = true;
//    static boolean flag = true;

    public static void main(String[] args) {

        // 建立一個跑 loop 的 thread，並透過 main thread 修改 flag，試圖終止 loop
        new Thread(() -> {
            System.out.println("---- come in");
            while (flag) {
                // 一般的變量，睡兩秒後才修改無法終止 while loop，thread 持續運作
                // volatile 變量，睡兩秒後才修改，因可見性可終止 while loop，thread 結束
            }
            System.out.println("---- flag is false, end loop...");
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = false;
        System.out.println("main modify flag finished");
    }
}
