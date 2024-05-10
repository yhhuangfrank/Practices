package com.frank.mytest.codetest.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest2 {
    public static void main(String[] args) {
        System.out.println("main thread top...");
        ExecutorService pool = Executors.newSingleThreadExecutor();

        try {
            pool.submit(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        int a = 1;
        if (a == 1); {
            System.out.println("a");
        }

        System.out.println("main thread bottom...");
        System.out.println(Thread.currentThread().isDaemon());
    }
}
