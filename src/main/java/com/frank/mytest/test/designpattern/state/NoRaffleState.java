package com.frank.mytest.test.designpattern.state;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 不能抽獎的狀態
 */
@NoArgsConstructor
@AllArgsConstructor
public class NoRaffleState extends State{

    RaffleActivity activity;

    @Override
    public void deductMoney() {
        System.out.println("扣除 50 元，可抽獎");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("扣了錢才能抽獎");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("目前狀態不能發放獎品");
    }
}
