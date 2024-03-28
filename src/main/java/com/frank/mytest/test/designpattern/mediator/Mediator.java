package com.frank.mytest.test.designpattern.mediator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Mediator {
    public abstract void register(String colleagueName, Colleague colleague);

    public abstract void getMessage(int changeState, String name);

    public abstract void sendMessage();
}
