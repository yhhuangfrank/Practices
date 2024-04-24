package com.frank.mytest.codetest.concurrent.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDownGradeDemo {

    public static void main(String[] args) {

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        // 同一個 main thread 裡，在寫的過程，可獲得讀鎖，稱為寫鎖降級
        writeLock.lock();

        System.out.println(Thread.currentThread().getName() + " write 操作...");
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " read 操作");
        readLock.unlock();

        writeLock.unlock();

    }
}
