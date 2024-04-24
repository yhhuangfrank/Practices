package com.frank.mytest.codetest.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskDemo {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("come in call()...");
            try {
                TimeUnit.SECONDS.sleep(5); // 假設任務需要 5 秒完成
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "task done";
        });
        new Thread(futureTask).start(); // 執行 Thread

        // 輪詢查看是否完成任務
        while (true) {
            if (futureTask.isDone()) {
                try {
                    System.out.println("任務完成了，結果為： "+futureTask.get());
                    break;
                } catch (InterruptedException | ExecutionException e) {
                   e.printStackTrace();
                }
            }
            // 間隔500毫秒再詢問，避免過多詢問耗費 CPU 資源
            System.out.println("執行任務中... 不要再催了");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
