package com.frank.mytest.codetest.concurrent.atomic;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 資源類，使用 AtomicInteger 保證原子操作
 */
@Getter
class MyNumber {
    private final AtomicInteger atomicNum = new AtomicInteger(); // 初始值為 0

    // i ++ 方法
    public void plusOne() {
        atomicNum.getAndIncrement();
    }
}

public class AtomicIntegerDemo {

    private static final int THREAD_SIZE = 50;

    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_SIZE);

        // 使用 50 個 thread 呼叫 MyNumber 操作，每個 thread 加一 1000 次
        for (int i = 1; i <= THREAD_SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.plusOne();
                    }
                } finally {
                    countDownLatch.countDown(); // 算完 1000 次計數
                }
            }, String.valueOf(i)).start();
        }

        // main thread 讀取最終加總後值
        try {
            countDownLatch.await(); // 確保 50 個 thread 執行完才讀取值
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + myNumber.getAtomicNum().get());

    }
}
