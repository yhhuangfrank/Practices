package com.frank.mytest.codetest.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

public class interruptDemo3 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("t1 stopped");
                    break;
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("發生異常，此時 t1.isInterrupted 為 " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt(); // 為何需要在出異常時再 interrupt，因為此時 isInterrupted 為 false
                    e.printStackTrace();
                }
                System.out.println("t1 is running..."); // 若出現異常，t1 會持續運行
            }
        }, "t1");
        t1.start();

        System.out.println("預設的 t1.isInterrupted 為 " + t1.isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(1); // sleep 1s;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1::interrupt, "t2").start(); // t2 調用 t1.interrupt()

    }
}

/**
 * 1. 中斷標示預設為 false
 * 2. t2 對 t1 發起 interrupt 協商，t1.isInterrupted 為 true
 * 3. 正常情況，程序停止
 * 4. 異常情況 在 sleep 時發生 InterruptedException，isInterrupted 會被清除變為 false，導致無限循環
 * 5. 因此需在 catch block 中，再次 interrupt
 */
