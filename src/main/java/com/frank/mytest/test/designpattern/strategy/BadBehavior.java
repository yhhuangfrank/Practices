package com.frank.mytest.test.designpattern.strategy;

public class BadBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飛行技術一般");
    }
}
