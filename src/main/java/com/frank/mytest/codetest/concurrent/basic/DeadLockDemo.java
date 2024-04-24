package com.frank.mytest.codetest.concurrent.basic;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {

        // 創建兩個 Thread，互相持有一個鎖，並試圖獲取對方的鎖
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "目前持有鎖 a，試圖獲取鎖 b");
                try {
                    TimeUnit.SECONDS.sleep(1); // 暫停 Thread，讓 race condition 更明顯
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (b) {
                    System.out.println("取得鎖 b");
                }
            }
        },"Thread A").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "目前持有鎖 b，試圖獲取鎖 a");
                try {
                    TimeUnit.SECONDS.sleep(1); // 暫停 Thread，讓 race condition 更明顯
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (a) {
                    System.out.println("取得鎖 a");
                }
            }
        },"Thread B").start();

    }
}
