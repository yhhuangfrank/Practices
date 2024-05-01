package com.frank.mytest.codetest.concurrent.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " 執行 callable 內方法，將返回結果");
//            Thread.sleep(2000);
            return 200;
        });

        // 因 FutureTask  有 implements Runnable，故可放入 Thread 建構子
        new Thread(task, "Thread A").start();

        // 透過 FutureTask 方法得到執行結果
        try {
            while (!task.isDone()) {
                System.out.println("等待 futureTask 執行完畢中...");
            }
            System.out.println("第一次獲取： " + task.get());
            System.out.println(Thread.currentThread().getName() + " -> 目前 Thread");
            System.out.println("第二次獲取： " + task.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
