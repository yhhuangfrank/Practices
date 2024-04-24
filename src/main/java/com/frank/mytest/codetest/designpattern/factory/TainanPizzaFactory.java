package com.frank.mytest.codetest.designpattern.factory;

public class TainanPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = Pizza.builder().type(type).area("Tainan").build();
        System.out.println("Tainan " + type + " pizza 創建完成");
        return pizza;
    }
}
