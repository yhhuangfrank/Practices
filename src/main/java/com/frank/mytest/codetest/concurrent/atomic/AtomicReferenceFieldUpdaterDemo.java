package com.frank.mytest.codetest.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

// 資源類
class MyClass {
    public volatile Boolean isInit = false;

    static final AtomicReferenceFieldUpdater<MyClass, Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyClass.class, Boolean.class, "isInit");

    public void init(MyClass myClass) {
        if (referenceFieldUpdater.compareAndSet(myClass, false, true)) {
            // 若尚未有 thread 進行初始化
            System.out.println(Thread.currentThread().getName() + "\t" + "進行初始化，大約需 2 秒");
            try {
                TimeUnit.SECONDS.sleep(2); // 假設花費 2 秒
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "初始化完成");
        } else {
            // 若已有 thread 進行初始化
            System.out.println(Thread.currentThread().getName() + "\t" + "已有其他 thread 進行初始化");
        }
    }
}

/**
 * 多執行緒并發調用一個類的初始化方法，如尚未被初始化，則執行初始化
 * 最多只會有一個執行緒操作成功，初始化只進行一次
 */
public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        for (int i = 1; i <= 5 ; i++) {
            new Thread(() -> {
                myClass.init(myClass);
            }, String.valueOf(i)).start();
        }
    }
}
