package com.frank.mytest.test.designpattern.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * 1. 包含最新天氣情況和資訊
 * 2. 包含觀察者集合，使用 ArrayList 管理
 * 3. 當數據有更新時，就主動的調用 ArrayList 通知所有的接入方，獲取最新資訊
 */
@Getter
@Setter
public class WeatherData implements Subject{
    private float temperature;
    private float pressure;
    private float humidity;
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.temperature, this.pressure, this.humidity);
        }
    }
}
