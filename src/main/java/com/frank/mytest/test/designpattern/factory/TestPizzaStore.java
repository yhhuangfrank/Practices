package com.frank.mytest.test.designpattern.factory;

public class TestPizzaStore {
    public static void main(String[] args) {

        // 使用子類決定創建細節
        new PizzaStore(new TainanPizzaFactory());
//        new PizzaStore(new TaipeiPizzaFactory());
    }
}
