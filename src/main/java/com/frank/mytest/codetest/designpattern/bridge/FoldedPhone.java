package com.frank.mytest.codetest.designpattern.bridge;

public class FoldedPhone extends Phone {
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println(" 折疊手機 ");
    }

    @Override
    public void close() {
        super.close();
        System.out.println(" 折疊手機 ");
    }

    @Override
    public void call() {
        super.call();
        System.out.println(" 折疊手機 ");
    }
}
