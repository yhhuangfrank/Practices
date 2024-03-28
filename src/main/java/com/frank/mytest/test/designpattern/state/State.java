package com.frank.mytest.test.designpattern.state;

public abstract class State {

    public abstract void deductMoney(); // 扣除積分
    public abstract boolean raffle(); // 是否抽中
    public abstract void dispensePrize(); // 發放
}
