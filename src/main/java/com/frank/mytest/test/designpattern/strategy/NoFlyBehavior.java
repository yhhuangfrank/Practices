package com.frank.mytest.test.designpattern.strategy;

public class NoFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("不會飛");
    }
}
