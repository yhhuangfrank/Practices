package com.frank.mytest.codetest.designpattern.singleton.doublecheck2;

public class SingletonTest2 {

    public static void main(String[] args) {

        System.out.println("靜態內部類實現單例");

        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

    }
}

class Singleton {
    private Singleton() {}

    // 編寫靜態內部類，在 Singleton 實例化時並不會立即執行，達到 lazy-loading 效果
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE; // 取得內部類時，底層裝載機制是 thread-safe 的
    }

}
