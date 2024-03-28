package com.frank.mytest.test.designpattern.strategy;

public class ToyDuck extends Duck {

    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("這是玩具鴨");
    }
}
