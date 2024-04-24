package com.frank.mytest.codetest.designpattern.facade;

import lombok.Getter;

public class PopcornMachine {

    // 使用單例模式
    @Getter
    private static final PopcornMachine instance = new PopcornMachine();

    public void on() {
        System.out.println(" popcorn on ");
    }

    public void off() {
        System.out.println(" popcorn off ");
    }

    public void play() {
        System.out.println(" popcorn is playing ");
    }

    public void pause() {
        System.out.println(" popcorn is paused ");
    }
}
