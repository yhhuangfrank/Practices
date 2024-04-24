package com.frank.mytest.codetest.designpattern.mediator;

public class TV extends Colleague{

    public TV(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int changeState) {

    }

    public void start() {
        System.out.println("開啟電視");
    }

    public void stop() {
        System.out.println("關閉電視");
    }
}
