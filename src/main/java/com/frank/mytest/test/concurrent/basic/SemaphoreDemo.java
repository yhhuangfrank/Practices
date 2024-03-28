package com.frank.mytest.test.concurrent.basic;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 6 輛汽車，搶 3 個車位
public class SemaphoreDemo {

    public static void main(String[] args) {

        // 建立許可管理 (Semaphore 物件)
        Semaphore semaphore = new Semaphore(3); // 6 台車搶 3 個位置

        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 獲取車位
                    System.out.println("--> " + Thread.currentThread().getName() + " 號車搶到位置");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5)); // 停 1 ～ 5 秒
                    System.out.println(Thread.currentThread().getName() + " 號車離開了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release(); // 釋放車位
                }
            }, String.valueOf(i)).start();
        }
    }
}
