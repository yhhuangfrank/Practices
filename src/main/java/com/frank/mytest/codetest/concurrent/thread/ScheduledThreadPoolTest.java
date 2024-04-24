package com.frank.mytest.codetest.concurrent.thread;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    static ScheduledFuture<?> t;

    static class MyTask implements Runnable {

        public void run() {
            System.out.println("print");
        }
    }

    public static void main(String[] args) throws InterruptedException {
       executor.scheduleAtFixedRate(new MyTask(), 0, 1, TimeUnit.SECONDS);

       TimeUnit.SECONDS.sleep(10);
       executor.shutdown();
    }
}
