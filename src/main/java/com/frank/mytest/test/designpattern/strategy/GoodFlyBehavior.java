package com.frank.mytest.test.designpattern.strategy;

public class GoodFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飛翔技術高超");
    }
}
