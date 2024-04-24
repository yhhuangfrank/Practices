package com.frank.mytest.codetest.designpattern.state;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Random;


/**
 * 可抽獎狀態
 */
@NoArgsConstructor
@AllArgsConstructor
public class CanRaffleState extends State{

    private RaffleActivity activity;

    @Override
    public void deductMoney() {
        System.out.println("已經扣除過");
    }

    @Override
    public boolean raffle() {
        System.out.println("抽獎開始");
        Random random = new Random();
        int num = random.nextInt(10);
        // 10% 機會
        if (num == 0) {
            // 改變活動狀態為發放獎品
            System.out.println("恭喜中獎了");
            activity.setState(activity.getDispenseState());
            return true;
        }
        System.out.println("沒有抽中獎品");
        activity.setState(activity.getNoRaffleState()); // 改為不能抽獎
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("尚未中獎，不能發放獎品");
    }
}
