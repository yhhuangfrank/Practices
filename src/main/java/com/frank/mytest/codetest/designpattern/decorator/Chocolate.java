package com.frank.mytest.codetest.designpattern.decorator;

public class Chocolate extends Decorator{
    public Chocolate(Drink drink) {
        super(drink);
        setDes(" chocolate ");
        setPrice(3.0f); // 加上佐料價格
    }
}
