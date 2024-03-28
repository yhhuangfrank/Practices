package com.frank.mytest.test.designpattern.factory;

import java.util.Scanner;

public class PizzaStore {

    // 依賴抽象層
    public PizzaStore(PizzaFactory pizzaFactory) { // 根據引入不同地區的工廠而定
        System.out.println("開始 create pizza");
        this.preparePizza(pizzaFactory);
    }

    private void preparePizza(PizzaFactory pizzaFactory) {
        Pizza p;
        do {
            String type = this.getType();
            if (type.equals("cheese")) {
                p = pizzaFactory.createPizza("cheese");
                System.out.println(p);
            } else if (type.equals("beef")) {
                p = pizzaFactory.createPizza("beef");
                System.out.println(p);
            } else {
                System.out.println("程式結束");
                break;
            }
        } while (true);
    }

    private String getType() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
