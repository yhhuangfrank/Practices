package com.frank.mytest.codetest.concurrent.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyResource {
    boolean isEdited;
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void test() {
        rwlock.readLock().lock();
        if (!isEdited) {
            try {
                rwlock.readLock().unlock();
                rwlock.writeLock().lock();
//                if (!isEdited) {
                    isEdited = true;
//                }
                System.out.println(Thread.currentThread().getName() + " 修改成功");
                rwlock.readLock().lock();
            } finally {
                rwlock.writeLock().unlock();
            }
        }

        System.out.println(Thread.currentThread().getName() + " 目前 isEdited = " + isEdited);
        rwlock.readLock().unlock();

    }
}

public class ReadWriteLockDemo2 {

    public static void main(String[] args) {
        MyResource resource = new MyResource();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                resource.test();
            },String.valueOf(i)).start();
        }


    }
}
