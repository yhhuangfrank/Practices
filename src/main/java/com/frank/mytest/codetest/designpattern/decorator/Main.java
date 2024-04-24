package com.frank.mytest.codetest.designpattern.decorator;

public class Main {
    public static void main(String[] args) {

        // 用裝飾者模式點一杯飲料 ： 2 份巧克力 + 1 份牛奶的 LongBlack

        // 1. 點一份 LongBlack
        Drink drink = new LongBlack();

        System.out.println(drink.getDes() + ", 費用1 : " + drink.calculateCost());

        // 加一份牛奶
        drink = new Milk(drink);
        System.out.println(drink.getDes() + ", 費用2 : " + drink.calculateCost());

        // 加一份巧克力
        drink = new Chocolate(drink);
        System.out.println(drink.getDes() + ", 費用3 : " + drink.calculateCost());

        // 加一份巧克力
        drink = new Chocolate(drink);
        System.out.println(drink.getDes() + ", 費用4 : " + drink.calculateCost());

    }
}
