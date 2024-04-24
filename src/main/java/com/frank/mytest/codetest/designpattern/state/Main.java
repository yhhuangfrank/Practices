package com.frank.mytest.codetest.designpattern.state;

public class Main {
    public static void main(String[] args) {

        // 建立活動，獎品有一個
        RaffleActivity activity = new RaffleActivity(1);

        // 連續抽
        for (int i = 0; i < 20; i++) {
            System.out.println("==== 第 " + (i + 1) + " 次抽獎 ====");
            activity.deductMoney(); // 扣除
            activity.raffle(); // 抽獎
        }
    }
}
