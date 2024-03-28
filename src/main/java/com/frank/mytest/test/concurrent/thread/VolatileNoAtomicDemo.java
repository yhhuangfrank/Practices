package com.frank.mytest.test.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class VolatileNoAtomicDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myResource.plusPlus();
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(myResource.num);

    }
}

class MyResource {
//    int num;
    volatile int num; // 若沒加上鎖，不保證原子性

//    public synchronized void plusPlus() {
//        num++;
//    }
    public void plusPlus() {
        num++;
    }
}
