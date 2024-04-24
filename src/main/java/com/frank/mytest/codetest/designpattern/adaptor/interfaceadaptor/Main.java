package com.frank.mytest.codetest.designpattern.adaptor.interfaceadaptor;

public class Main {
    public static void main(String[] args) {

        AbstractAdaptor abstractAdaptor = new AbstractAdaptor() {
            @Override
            public void method1() {
                System.out.println("只改寫 method1");
            }
        };

        abstractAdaptor.method1();
    }
}
