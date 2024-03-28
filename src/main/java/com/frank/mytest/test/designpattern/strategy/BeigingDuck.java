package com.frank.mytest.test.designpattern.strategy;

public class BeigingDuck extends Duck{

    public BeigingDuck() {
        flyBehavior = new BadBehavior();
    }

    @Override
    public void display() {
        System.out.println("這是北京鴨");
    }
}
