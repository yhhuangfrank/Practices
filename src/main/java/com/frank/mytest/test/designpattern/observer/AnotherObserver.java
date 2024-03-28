package com.frank.mytest.test.designpattern.observer;

public class AnotherObserver implements Observer{
    @Override
    public void update(float temperature, float pressure, float humidity) {
        System.out.println("=== 2 號觀察者 ===");
        System.out.println("temp : " + temperature);
        System.out.println("pressure : " + pressure);
        System.out.println("humidity : " + humidity);
    }
}
