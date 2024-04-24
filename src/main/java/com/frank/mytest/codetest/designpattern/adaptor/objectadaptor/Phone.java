package com.frank.mytest.codetest.designpattern.adaptor.objectadaptor;

public class Phone {

    // 接受一個適配器抽象類
    public void charging(Voltage5V voltage5V) {
        if (voltage5V.outPut5V() == 5) {
            System.out.println("可以充電！");
            return;
        }
        System.out.println("無法充電！");
    }
}
