package com.frank.mytest.test.designpattern.mediator;

public class CoffeeMachine extends Colleague {

    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name, this); // 將自身放入 mediator 中
    }

    @Override
    public void sendMessage(int changeState) {
        this.mediator.getMessage(changeState, this.name);
    }

    public void start() {
        System.out.println("開始煮咖啡");
    }
}
