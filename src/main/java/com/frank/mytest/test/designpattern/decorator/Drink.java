package com.frank.mytest.test.designpattern.decorator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Drink {

    private String des; // 描述
    private float price = 0.0f;

    // 計算費用的抽象方法，由子類實現
    public abstract float calculateCost();
}
