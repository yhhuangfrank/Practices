package com.frank.mytest.codetest.designpattern.state;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DispenseOutState extends State{

    private RaffleActivity activity;

    @Override
    public void deductMoney() {
        System.out.println("獎品發送完畢，請下次再參加");
    }

    @Override
    public boolean raffle() {
        System.out.println("獎品發送完畢，請下次再參加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("獎品發送完畢，請下次再參加");
    }
}
