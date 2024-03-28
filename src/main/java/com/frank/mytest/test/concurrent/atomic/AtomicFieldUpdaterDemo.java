package com.frank.mytest.test.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class BankAccount {

    String bankName = "MyBank";

    public volatile int money;

    /**
     * 因為物件的屬性修改類型原子類都是抽象類，所以每次使用時必須使用靜態方法 newUpdater() 創建一個更新器
     * 並且需要設置想要更新的類和屬性
     */
    static final AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    // 以往做法
//    public synchronized void add() {
//        money++;
//    }

    // 不加 synchronized (鎖整個物件) ，使用 fieldUpdater 進行局部鎖 (微創小手術)
    public void atomicAdd (BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

/**
 * 以 thread-safe 方式操作 non thread-safe 物件的
 * 某些屬性
 * 需求：
 * 使用 10 個 thread ，每個轉帳 1000, 不使用 synchronized ，改用 AtomicFieldUpdater
 */
public class AtomicFieldUpdaterDemo {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10 ; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000 ; j++) {
//                        bankAccount.add();
                        bankAccount.atomicAdd(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + "\t result = " + bankAccount.money);

    }
}
