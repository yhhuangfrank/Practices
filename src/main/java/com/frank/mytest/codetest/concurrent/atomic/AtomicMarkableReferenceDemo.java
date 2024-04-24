package com.frank.mytest.codetest.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 首次獲取標記: " + marked);

            try {
                TimeUnit.SECONDS.sleep(1); // 確保 t2 也拿到 (100, false)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            markableReference.compareAndSet(100, 101, marked, !marked);

        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 首次獲取標記: " + marked);

            try {
                TimeUnit.SECONDS.sleep(2); // 2 秒鐘後修改
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = markableReference.compareAndSet(100, 2023, marked, !marked);
            System.out.println(Thread.currentThread().getName() + " 修改是否成功 " + b);
            System.out.println(Thread.currentThread().getName() + " 最後 isMarked = " + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + " 最後 reference =  " + markableReference.getReference());

        }, "t2").start();
    }
}
