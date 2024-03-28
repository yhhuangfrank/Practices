package com.frank.mytest.test.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) {

        // 使用 LockSupport 進行阻塞與通知

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000); // 若 t1 較後運行，只要有通行證依舊可解除阻塞狀態
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " come in");
            System.out.println("current time is " + System.currentTimeMillis());
            LockSupport.park();
//            LockSupport.park(); // 調用兩次 park 需要兩張通行證，故阻塞在第二次調用
            System.out.println("current time is " + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " end");
        }, "t1");
        t1.start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            LockSupport.unpark(t1);
        }, "t2").start();

    }
}
