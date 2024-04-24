package com.frank.mytest.codetest.designpattern.responsibilitychain;

import lombok.Getter;
import lombok.Setter;

// 定義一個處理請求的介面或抽象類，同時含有另外一個 Approver
@Getter
@Setter
public abstract class Approver {

    protected Approver successor;
    protected String name;

    protected Approver(String name) {
        this.name = name;
    }

    // 處理請求的方法，交由子類處理
    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
