package com.frank.mytest.codetest.designpattern.strategy;

public class NoFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("不會飛");
    }
}
