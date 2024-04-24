package com.frank.mytest.codetest.designpattern.decorator;

public class Soy extends Decorator{
    public Soy(Drink drink) {
        super(drink);
        setDes(" soy ");
        setPrice(1.5f); // 加上佐料價格
    }
}
