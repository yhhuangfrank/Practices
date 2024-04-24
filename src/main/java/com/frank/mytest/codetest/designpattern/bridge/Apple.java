package com.frank.mytest.codetest.designpattern.bridge;

public class Apple implements Brand {
    @Override
    public void open() {
        System.out.println("Apple phone open");
    }

    @Override
    public void close() {
        System.out.println("Apple phone close");

    }

    @Override
    public void call() {
        System.out.println("Apple phone call");

    }
}
