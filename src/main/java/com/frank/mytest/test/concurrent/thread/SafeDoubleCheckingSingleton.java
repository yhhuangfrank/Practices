package com.frank.mytest.test.concurrent.thread;

public class SafeDoubleCheckingSingleton {

    private volatile static SafeDoubleCheckingSingleton singleton;

    private SafeDoubleCheckingSingleton() {}

    // 雙重鎖設計
    public static SafeDoubleCheckingSingleton getInstance() {
        // 加鎖之前之後判斷兩次 (double check)
        if (singleton == null) {
            // 確保只有一個 thread 能創建物件
            synchronized (SafeDoubleCheckingSingleton.class) {
                // 隱患：多執行緒下，由於指令重排序，可能發生 singleton 還沒有賦值就被其他 thread 讀取的情形
                // 解決方式：將變量宣告為 volatile，解決 初始化 與 賦值 這兩步驟發生重新排序
                if (singleton == null) {
                    singleton = new SafeDoubleCheckingSingleton();
                }
            }
        }
        // 有物件時，直接返回
        return singleton;
    }
}
