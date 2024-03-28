package com.frank.mytest.test.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

public class interruptDemo2 {

    public static void main(String[] args) {

        // 實例方法 interrupt 僅是設置狀態，並不能真正中斷 thread
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("i = " + i);
            }
            System.out.println("finish for loop, t1.isInterrupted is "+ Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("預設的 t1.isInterrupted 為 " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2); // sleep 2ms;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt(); // interrupt t1
        System.out.println("call interrupt() after 2 ms, current t1.isInterrupted is "+ t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2000); // sleep 2s;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("call interrupt() after 2000 ms, current t1.isInterrupted is "+ t1.isInterrupted());
    }
}
