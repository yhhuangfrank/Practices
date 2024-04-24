package com.frank.mytest.codetest.concurrent.basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) {

        // 設置計數器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        // 模擬 6 個同學離開教室後，班長才鎖門
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
//                try {
//                    Thread.sleep(6*1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(Thread.currentThread().getName() + " 號同學離開教室...");
                // 計數器減 1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 執行 await() 確保確實數到 0 了，此時 main thread 為 block
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("目前 thread 為：" + Thread.currentThread().getName() + "， 班長鎖門");


    }
}
