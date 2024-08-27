package com.frank.mytest.codetest.other;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (atomicInteger.get() == 10) {
                        throw new RuntimeException("error!");
                    } else {
                        System.out.print("get integer: ");
                        System.out.println(atomicInteger.getAndIncrement());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, 2000, 10000);

        System.out.println("main end");
    }
}
