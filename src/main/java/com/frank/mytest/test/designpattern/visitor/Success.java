package com.frank.mytest.test.designpattern.visitor;

public class Success extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("man 給好評");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("woman 給好評");
    }
}
