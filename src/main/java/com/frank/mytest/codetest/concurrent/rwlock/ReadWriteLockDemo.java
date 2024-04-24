package com.frank.mytest.codetest.concurrent.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Resource {
    Map<String, String> map = new HashMap<>();
    ReentrantLock lock = new ReentrantLock();
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
//        lock.lock();
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在寫入");
            map.put(key, value);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(Thread.currentThread().getName() + " 完成寫入");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            lock.unlock();
            rwlock.writeLock().unlock();
        }
    }

    public void read(String key) {
//        lock.lock();
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在讀取");
            map.get(key);
//            TimeUnit.MILLISECONDS.sleep(200);
            TimeUnit.MILLISECONDS.sleep(2000); // 展示當讀比較慢時，讀鎖沒有完成，不能做寫入
            System.out.println(Thread.currentThread().getName() + " 完成讀取");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            lock.unlock();
            rwlock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();
        //  寫讀分別 10 個 thread
        for (int i = 1; i <= 10; i++) {
            final int num = i;
            new Thread(() -> {
                resource.write(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 10; i++) {
            final int num = i;
            new Thread(() -> {
                resource.read(num + ""); // 共享讀
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 3; i++) {
            final int num = i;
            new Thread(() -> {
                resource.write(num + "", num + ""); // 新的寫入操作在讀尚未完成之前不能寫入
            }, "新寫入 Thread " + i).start();
        }
    }
}
