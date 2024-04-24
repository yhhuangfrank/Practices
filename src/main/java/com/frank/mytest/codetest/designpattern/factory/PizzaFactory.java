package com.frank.mytest.codetest.designpattern.factory;

public interface PizzaFactory {

    // 讓子類工廠實現
    Pizza createPizza(String type);
}
