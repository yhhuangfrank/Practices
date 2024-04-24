package com.frank.mytest.codetest.designpattern.prototype.shallowclone;

public class Main {
    public static void main(String[] args) {

        Sheep sheep = new Sheep("no1", 1, "white");

        Sheep sheep1 = sheep.clone();
        Sheep sheep2 = sheep.clone();
        System.out.println(sheep);
        System.out.println(sheep1);
        System.out.println(sheep2);
    }
}
