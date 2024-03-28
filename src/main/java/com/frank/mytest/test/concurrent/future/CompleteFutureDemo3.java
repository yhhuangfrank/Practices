package com.frank.mytest.test.concurrent.future;

import java.util.concurrent.*;

public class CompleteFutureDemo3 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 異步任務接續執行
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " come in step 1 ");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }, threadPool)
                .thenApply(v -> {
                    System.out.println(Thread.currentThread().getName() + " come in step 2 ");
                    System.out.println("in step 2, v = " + v);
//                    int a = 1 / 0; // 測試異常發生
                    return v + 1;
                })
                .thenApply(v -> {
                    System.out.println(Thread.currentThread().getName() + " come in step 3 ");
                    System.out.println("in step 2, v = " + v);
                    return v + 3;
                })
                .whenComplete((value, e) -> {
                    if (e == null) { // 沒有 exception 產生
                        System.out.println("任務成功！ 得到結果為： " + value);
                    }
                }).exceptionally(e -> {
                    System.out.println("發生錯誤 : " + e.getCause() + "\n" + e.getMessage());
                    e.printStackTrace();
                    return null; // 當錯誤發生時，回傳 null 結果
                });

        System.out.println("main 執行其他任務");

        System.out.println("取得回傳結果： " + future.join());

        threadPool.shutdown();

    }
}
