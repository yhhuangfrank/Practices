package com.frank.mytest.test.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 透過不同方式實現 thread 的中斷
 */
public class InterruptDemo {

    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false); // 透過 AtomicBoolean 實現

    public static void main(String[] args) {

        // 兩個 thread，一個運行，另一個透過設置來中斷第一個 thread 運行
//        volatileMethod();
//        atomicMethod();

        threadApiMethod();

    }

    private static void threadApiMethod() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "----> is stopped");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }, "t1");
        t1.start();

        System.out.println("預設的 interrupted 狀態為： " + t1.isInterrupted());

        // 暫停 20 ms
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt(); // t2 告訴 t1 需中斷
            System.out.println(Thread.currentThread().getName() + " is interrupting t1 thread");
        }, "t2").start();
    }

    private static void atomicMethod() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "----> is stopped");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }, "t1").start();

        // 暫停 20 ms
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
            System.out.println(Thread.currentThread().getName() + " is interrupting t1 thread");
        }, "t2").start();
    }

    private static void volatileMethod() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "----> is stopped");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }, "t1").start();

        // 暫停 20 ms
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
            System.out.println(Thread.currentThread().getName() + " is running");
        }, "t2").start();
    }
}
