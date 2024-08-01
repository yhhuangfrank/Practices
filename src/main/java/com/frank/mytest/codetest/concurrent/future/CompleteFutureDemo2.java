package com.frank.mytest.codetest.concurrent.future;

import java.util.concurrent.*;

public class CompleteFutureDemo2 {

    public static void main(String[] args) {

//        // 無傳遞 threadPool
//        CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + " come in ");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "complete task";
//        }).whenComplete((value, e) -> {
//            if (e == null) { // 沒有 exception 產生
//                System.out.println("任務成功！ 得到結果為： " + value);
//            }
//        }).exceptionally(e -> {
//            System.out.println("發生錯誤 : " + e.getCause() + "\n" + e.getMessage());
//            e.printStackTrace();
//            return null; // 當錯誤發生時，回傳 null 結果
//        });
//
//        System.out.println("main 執行其他任務");
//        // 若使用預設的 ForkJoinPool，當 main thread 結束時，ForkJoinPool 就被馬上關閉，因此會需要讓
//        // main thread 暫停才能得到 async 任務執行過程的輸出
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 有傳遞 threadPool，不需暫停 main thread 也能輸出 when complete 過程
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + " come in ");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a = 1 / 0;
                return Thread.currentThread().getName() + " complete task";
            }, threadPool).whenComplete((value, e) -> {
                if (e == null) { // 沒有 exception 產生
                    System.out.println("任務成功！ 得到結果為： " + value);
                }
            }).exceptionally(e -> {
                System.out.println("發生錯誤 : " + e.getCause() + "\n" + e.getMessage());
                e.printStackTrace();
                return null; // 當錯誤發生時，回傳 null 結果
            });

            System.out.println("main 執行其他任務");

            try {
                System.out.println("取得回傳結果： " + future.get());
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            System.out.println("發生預期外錯誤！" + e.getMessage());
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
