package com.frank.mytest.codetest.designpattern.mediator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 協同工作類，底下有不同的工作者
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Colleague {
    protected Mediator mediator;
    protected String name;

    public abstract void sendMessage(int changeState);
}
