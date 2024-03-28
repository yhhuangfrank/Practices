package com.frank.mytest.test.designpattern.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class Duck {
    protected FlyBehavior flyBehavior;

    public abstract void display(); // 顯示鴨子訊息

    public void quack() {
        System.out.println("鴨子呱呱叫");
    }

    public void swim() {
        System.out.println("鴨子會游泳");
    }

    public void fly() {
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }
}
