package com.frank.mytest.test.designpattern.observer;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer observer1 = new CurrentConditions();
        Observer observer2 = new AnotherObserver();
        weatherData.registerObserver(observer1);
        weatherData.registerObserver(observer2);

        weatherData.setTemperature(10f);
        weatherData.setPressure(100f);
        weatherData.setHumidity(20f);

        System.out.println("目前最新數據為");
        weatherData.notifyObservers();

        weatherData.removeObserver(observer1);

        System.out.println("第二次推送");
        weatherData.notifyObservers();
    }
}
