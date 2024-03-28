package com.frank.mytest.test.designpattern.factory;

public class TaipeiPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = Pizza.builder().type(type).area("Taipei").build();
        System.out.println("Taipei " + type + " pizza 創建完成");
        return pizza;
    }
}
