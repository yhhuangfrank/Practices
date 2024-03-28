package com.frank.mytest.test.designpattern.decorator;

public class Coffee extends Drink{

    // 無佐料的飲料花費等同其價格
    @Override
    public float calculateCost() {
        return super.getPrice();
    }
}
