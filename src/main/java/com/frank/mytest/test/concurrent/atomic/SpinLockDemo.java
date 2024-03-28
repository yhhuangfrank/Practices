package com.frank.mytest.test.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋鎖好處：循環比較方式沒有類似 wait() 的阻塞
 *
 * 示範例子：thread A 先進來調用 lock 方法自己持有鎖 5 秒鐘，隨後 thread B 進來
 * 發現目前有其他 thread 持有鎖，透過自旋直到 thread A 釋放
 */
public class SpinLockDemo {

    // 使用 AtomicReference 對執行緒實現自旋鎖
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    // lock 方法
    public void lock() {
        System.out.println(Thread.currentThread() + "---- come in");
        Thread currentThread = Thread.currentThread();
        // 當目前 compare 後，發現是 null，才設置進去
        while (!threadAtomicReference.compareAndSet(null, currentThread)) {
        }
        // 執行後續操作
    }

    // unlock 方法
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        // 將目前自己的 reference 移除
        threadAtomicReference.compareAndSet(currentThread, null);
        System.out.println(Thread.currentThread() + "---- task over, unlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            // 調用 lock
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unlock();
        },"A").start();

        // 暫停 500 ms, 保證 A 先啟動
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unlock();
        },"B").start();

    }
}
