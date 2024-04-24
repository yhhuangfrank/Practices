package com.frank.mytest.codetest.designpattern.adaptor.objectadaptor;

import com.frank.mytest.codetest.designpattern.adaptor.classadaptor.Voltage220V;

// 進行適配轉換
public class VoltageAdaptor implements Voltage5V {
    private final Voltage220V voltage220V;

    public VoltageAdaptor(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int outPut5V() {
        if (voltage220V != null) {
            int src = voltage220V.outPut220V(); // 獲取 220V
            return src / 44; // 轉為 5V
        }
        return 0;
    }
}
