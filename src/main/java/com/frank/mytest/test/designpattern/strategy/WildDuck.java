package com.frank.mytest.test.designpattern.strategy;


public class WildDuck extends Duck{

    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("這是野鴨");
    }
}
