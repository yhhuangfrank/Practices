package com.frank.mytest.codetest.designpattern.adaptor.classadaptor;

public class Main {
    public static void main(String[] args) {

        Phone phone = new Phone();
        phone.charging(new VoltageAdaptor());
    }
}
