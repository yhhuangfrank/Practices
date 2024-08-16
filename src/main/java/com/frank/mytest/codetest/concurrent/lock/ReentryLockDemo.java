package com.frank.mytest.codetest.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentryLockDemo {

    private final Object object = new Object();

    private Lock lock = new ReentrantLock();

    public void entry01() {
        // 需要可重入性才可達成
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t" + "外層調用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "中層調用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "內層調用");
                    }
                }
            }
        }, "thread1").start();
    }

    public void entry02() {
        m1();
    }

    private synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t" + "外層調用");
        m2();
    }

    private synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t" + "中層調用");
        m3();
    }

    private synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t" + "內層調用");
    }

    private void entry03() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "外層調用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t" + "內層調用");
                } finally {
                    lock.unlock(); // lock 幾次就要 unlock 幾次，否則會造成其他 thread 得不到鎖
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "外層調用");
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }


    public static void main(String[] args) {
        ReentryLockDemo reentryLockDemo = new ReentryLockDemo();
//        reentryLockDemo.entry01();
//        reentryLockDemo.entry02();
        reentryLockDemo.entry03();
    }
}
