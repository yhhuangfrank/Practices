package com.frank.mytest.test.designpattern.template;

public abstract class SoyMilk {
    // 模板方法, final 不讓子類覆蓋
    final void make() {
        select();
        addCondiments();
        soak();
        beat();
    }

    void select() {
        System.out.println("選擇黃豆");
    }

    // 添加不同配料，抽象方法，子類實現
    abstract void addCondiments();

    // 浸泡
    void soak() {
        System.out.println("浸泡");
    }

    void beat() {
        System.out.println("打碎");
    }
}
