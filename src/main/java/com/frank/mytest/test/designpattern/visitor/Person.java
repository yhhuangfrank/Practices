package com.frank.mytest.test.designpattern.visitor;

public abstract class Person {

    // 提供一個方法，讓訪問者訪問
    public abstract void accept(Action action);
}
