package com.frank.mytest.test.designpattern.adaptor.objectadaptor;

import com.frank.mytest.test.designpattern.adaptor.classadaptor.Voltage220V;

public class Main {
    public static void main(String[] args) {

        Phone phone = new Phone();
        phone.charging(new VoltageAdaptor(new Voltage220V()));
    }
}
