package com.frank.mytest.test.designpattern.facade;

public class Main {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.ready();
        facade.off();
    }
}
