package com.frank.mytest.test.concurrent.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    static int number = 10;
    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long l = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " 準備修改");
        try {
            number = number + 10;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockWrite(l);
        }
        System.out.println(Thread.currentThread().getName() + " 完成修改");
    }

    // 悲觀讀，讀沒有完成時，寫不能進來
    public void read() {
        long l = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + " 準備讀取，花費四秒鐘");
        try {
            TimeUnit.SECONDS.sleep(4);
            int result = number;
            System.out.println(Thread.currentThread().getName() + " 讀取數據 ： " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockRead(l);
        }
    }

    // 樂觀讀，讀沒有完成時，寫也進來
    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        // 故意間隔 4 秒鐘，樂觀認為讀取中沒有其他 thread 修改 number，使用 validate() 判斷
        // 若為 true 表示無人修改，false 則 表示已被修改
        System.out.println(Thread.currentThread().getName() + " 初始 validate:  " + stampedLock.validate(stamp));
        for (int i = 1; i <= 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 正在讀取 (" + i + " s) validate 回傳值為 " + stampedLock.validate(stamp));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (!stampedLock.validate(stamp)) {
            System.out.println("被修改過，需要升級鎖");
            stamp = stampedLock.readLock();
            try {
                result = number;
                System.out.println("升級為悲觀鎖，重新獲取 number : " + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        System.out.println(Thread.currentThread().getName() + " finally value is " + result);
    }

    public static void main(String[] args) {

        StampedLockDemo resource = new StampedLockDemo();
/**
 * 傳統方式
 */
//        new Thread(() -> {
//            resource.read();
//        }, "readThread").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " 進入 write");
//            resource.write();
//        }, "writeThread").start();

        /**
         * 使用樂觀讀
         */
        new Thread(() -> {
            resource.tryOptimisticRead();
        }, "readThread").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 進入 write");
            resource.write();
        }, "writeThread").start();

    }
}
