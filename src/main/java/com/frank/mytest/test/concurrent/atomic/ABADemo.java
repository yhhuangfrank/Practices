package com.frank.mytest.test.concurrent.atomic;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
//        abaHappen();
        resolveABA();
    }

    private static void resolveABA() {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread() +  " 首次版本為 ： " + stamp);
            // 確保 thread D 拿到的 stamp 也是 100
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread() +  " 2次版本為 ： " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread() +  " 3次版本為 ： " + atomicStampedReference.getStamp());

        }, "C").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 拿到版本 1
            System.out.println(Thread.currentThread() +  " 首次版本為 ： " + stamp);
            // 暫停 1 秒鐘，等待 thread C 發生 ABA 問題
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 期望版本為 1，但已被修改成 3，因此 set 失敗
            boolean b = atomicStampedReference.compareAndSet(100, 2023, stamp, stamp + 1);
            System.out.println(b + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());

        }, "D").start();
    }

    private static void abaHappen() {
        // 兩個 thread，thread Ａ 修改值又給回來，thread B 只檢查是否還是 100 時，就會修改成功 (但不知道中間是否動過)
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.compareAndSet(101, 100);
        },"A").start();

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.compareAndSet(100, 2022);
            // 查看最後的值是否為 2022
            System.out.println(atomicInteger.get());
        },"B").start();
    }
}
