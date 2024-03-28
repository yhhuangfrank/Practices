package com.frank.mytest.test.designpattern.facade;

import lombok.Getter;

public class DVDPlayer {

    // 使用單例模式
    @Getter
    private static final DVDPlayer instance = new DVDPlayer();

    public void on() {
        System.out.println(" dvd on ");
    }

    public void off() {
        System.out.println(" dvd off ");
    }

    public void play() {
        System.out.println(" dvd is playing ");
    }

    public void pause() {
        System.out.println(" dvd is paused ");
    }
}
