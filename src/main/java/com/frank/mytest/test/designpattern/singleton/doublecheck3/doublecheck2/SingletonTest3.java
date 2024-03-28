package com.frank.mytest.test.designpattern.singleton.doublecheck3.doublecheck2;

public class SingletonTest3 {

    public static void main(String[] args) {

        System.out.println("枚舉類實現單例");

        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;

        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

        instance1.test();
    }
}

enum Singleton {
    INSTANCE; // 唯一屬性

    public void test() {
        System.out.println("ok");
    }
}
