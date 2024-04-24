package com.frank.mytest.codetest.designpattern.decorator;

public class Milk extends Decorator{
    public Milk(Drink drink) {
        super(drink);
        setDes(" milk ");
        setPrice(2.0f); // 加上佐料價格
    }
}
