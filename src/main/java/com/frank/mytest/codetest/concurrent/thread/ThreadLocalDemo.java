package com.frank.mytest.codetest.concurrent.thread;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Data
class TicketSeller {
    private int saleNum;
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public synchronized void saleTicket() {
        saleNum++;
    }

    public void saleByThread() {
        threadLocal.set(1 + threadLocal.get());
    }
}


public class ThreadLocalDemo {

    public static void main(String[] args) {

        TicketSeller seller = new TicketSeller();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    int size = new Random().nextInt(5) + 1;
                    for (int j = 1; j <= size; j++) {
                        seller.saleTicket();
                        seller.saleByThread(); // 每個 thread 各自計算
                    }
                    System.out.println(Thread.currentThread().getName() + "號賣出 " + seller.getThreadLocal().get() + " 張");
                } finally {
                    seller.getThreadLocal().remove(); // 清出各自 thread 的變量空間，避免在 thread pool 複用時產生 memory leak
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("共賣出 " + seller.getSaleNum() + " 張");

    }

}
