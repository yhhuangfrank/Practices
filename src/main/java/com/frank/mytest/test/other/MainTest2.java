package com.frank.mytest.test.other;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainTest2 {
    public static void main(String[] args) {
        System.out.println("main thread top...");

        Executors.newSingleThreadExecutor().submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });

        System.out.println("main thread bottom...");

        Thread.currentThread().isDaemon();
    }
}
