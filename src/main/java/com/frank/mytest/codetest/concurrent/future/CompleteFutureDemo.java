package com.frank.mytest.codetest.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompleteFutureDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 無返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("come in runnable...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("come in runnable...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPool); // 可傳入 threadPool

        // 有返回值
        CompletableFuture<String> future2 =  CompletableFuture.supplyAsync(() -> {
            System.out.println("come in supplier...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "complete";
        });

        threadPool.shutdown();
    }
}
