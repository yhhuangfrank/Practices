package com.frank.mytest.codetest.designpattern.visitor;

public class Fail extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("man 給負評");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("woman 給負評");
    }
}
