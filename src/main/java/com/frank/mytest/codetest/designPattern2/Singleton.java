package com.frank.mytest.codetest.designPattern2;

public class Singleton {

    private static volatile Singleton singleton = null;
    private String value;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();

        System.out.println(s.getValue()); // null

        s.setValue("a value string");
        System.out.println(s.getValue()); // "a value string"

        Singleton s2 = Singleton.getInstance();

        System.out.println(s2.getValue()); // "a value string"


    }
}


