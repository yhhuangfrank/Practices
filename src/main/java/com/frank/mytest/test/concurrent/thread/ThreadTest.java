package com.frank.mytest.test.concurrent.thread;

public class ThreadTest {

    public static void main(String[] args) {

        System.out.println("main thread start =====");

        Thread thread = new Thread(() -> {
            System.out.println("thread runnning...");
        });
        thread.setDaemon(true);
        thread.start();

        System.out.println("main thread end =====");

    }
}
