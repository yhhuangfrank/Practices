package com.frank.mytest.test.concurrent.thread;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
class TicketSeller2 {
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void saleByThread() {
        threadLocal.set(1 + threadLocal.get());
    }
}


public class ThreadLocalDemo2 {

    public static void main(String[] args) {

        TicketSeller2 seller = new TicketSeller2();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before = seller.getThreadLocal().get();
                        seller.saleByThread();
                        Integer after = seller.getThreadLocal().get();
                        System.out.println(Thread.currentThread().getName() + "before: " + before + " after: " + after);
                    } finally {
                        seller.getThreadLocal().remove(); // 清出各自 thread 的變量空間，避免在 thread pool 複用時產生 memory leak
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }

}
