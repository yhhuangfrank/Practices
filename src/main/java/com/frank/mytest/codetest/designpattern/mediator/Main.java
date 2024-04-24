package com.frank.mytest.codetest.designpattern.mediator;

public class Main {
    public static void main(String[] args) {

        // 創建中介者物件
        Mediator mediator = new ConcreteMediator();

        // 創建 Alarm 並加入到 mediator 的 map
        Alarm alarm = new Alarm(mediator, "alarm");
        new CoffeeMachine(mediator, "coffeeMachine");
        new TV(mediator, "tv");

        alarm.sendAlarm(0);
        alarm.sendAlarm(1);
    }
}
