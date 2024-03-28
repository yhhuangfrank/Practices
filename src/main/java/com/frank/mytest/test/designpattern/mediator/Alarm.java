package com.frank.mytest.test.designpattern.mediator;


// 具體的工作者
public class Alarm extends Colleague{

    public Alarm(Mediator mediator, String name) {
       super(mediator, name);
       mediator.register(name, this); // 將自身放入 mediator 中
    }

    public void sendAlarm(int changeState) {
        this.sendMessage(changeState);
    }

    @Override
    public void sendMessage(int changeState) {
        // 調用中介者的方法
        this.getMediator().getMessage(changeState, this.name);
    }
}