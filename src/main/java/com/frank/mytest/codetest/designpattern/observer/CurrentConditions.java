package com.frank.mytest.codetest.designpattern.observer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentConditions implements Observer {
    private float temperature;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temperature, float pressure, float humidity) {
        System.out.println("=== 1 號觀察者 ===");
        System.out.println("temp : " + temperature);
        System.out.println("pressure : " + pressure);
        System.out.println("humidity : " + humidity);
    }
}
