package com.frank.mytest.codetest.designpattern.adaptor.classadaptor;

// 進行適配轉換
public class VoltageAdaptor extends Voltage220V implements Voltage5V{
    @Override
    public int outPut5V() {
        int src = outPut220V(); // 獲取 220V
        return src / 44; // 轉為 5V
    }
}
