package com.frank.mytest.codetest.designpattern.strategy;

public class BadBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飛行技術一般");
    }
}
