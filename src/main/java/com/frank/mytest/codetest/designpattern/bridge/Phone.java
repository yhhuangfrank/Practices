package com.frank.mytest.codetest.designpattern.bridge;

public abstract class Phone {
    // 品牌
    private final Brand brand;

    protected Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        this.brand.close();
    }

    protected void call() {
        this.brand.call();
    }
}
