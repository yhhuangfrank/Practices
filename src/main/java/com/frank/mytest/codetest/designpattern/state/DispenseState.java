package com.frank.mytest.codetest.designpattern.state;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DispenseState extends State{
    private RaffleActivity activity;

    @Override
    public void deductMoney() {
        System.out.println("不能扣除積分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽獎");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("開始發放獎品");
        if (activity.getCount() > 0) {
            activity.setState(activity.getNoRaffleState());
            return;
        }
        System.out.println("很遺憾，獎品已發放完畢");
        System.exit(0);
//        activity.setState(activity.getDispenseOutState());
    }
}
