package com.frank.mytest.codetest.designPattern2.singleton2;

public class Singleton {

    // 外部不能 new
    private Singleton() {
        System.out.println("in Singleton constructor...");
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
