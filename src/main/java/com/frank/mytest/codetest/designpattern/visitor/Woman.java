package com.frank.mytest.codetest.designpattern.visitor;

public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
