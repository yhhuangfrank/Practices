package com.frank.mytest.codetest.designpattern.adaptor.objectadaptor;

// 被適配類
public class Voltage220V {
    public int outPut220V() {
        int src = 220;
        System.out.println("電壓： " + src);
        return src;
    }
}
