package com.frank.mytest.codetest.concurrent.interrupt;

public class interruptDemo4 {

    public static void main(String[] args) {

        // 測試目前 thread 是否已被中斷(檢查中斷標示)，返回一個 boolean 並清除中斷標示
        // 第二次調用時中斷狀態已被清除，返回 false
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());// false
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());// false
        System.out.println("--1");
        Thread.currentThread().interrupt(); // 手動將目前 thread interrupt 狀態設為 true
        System.out.println("--2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());// true
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());// false
    }
}
