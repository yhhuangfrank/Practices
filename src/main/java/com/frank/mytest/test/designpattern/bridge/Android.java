package com.frank.mytest.test.designpattern.bridge;

public class Android implements Brand {
    @Override
    public void open() {
        System.out.println("Android phone open");
    }

    @Override
    public void close() {
        System.out.println("Android phone open");
    }

    @Override
    public void call() {
        System.out.println("Android phone call");
    }
}
