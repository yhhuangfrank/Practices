package com.frank.mytest.codetest.designpattern.strategy;

public class Main {
    public static void main(String[] args) {

        ToyDuck toyDuck = new ToyDuck();
        toyDuck.fly();

        BeigingDuck beigingDuck = new BeigingDuck();
        beigingDuck.fly();
    }
}
