package com.frank.mytest.test.designpattern.state;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RaffleActivity {
    private State state; // 變化的活動目前狀態
    private int count = 0;
    private State noRaffleState = new NoRaffleState(this);
    private State canRaffleState = new CanRaffleState(this);
    private State dispenseState = new DispenseState(this);
    private State dispenseOutState = new DispenseOutState(this);

    // 指定一個獎品數，舉辦抽獎活動
    public RaffleActivity(int count) {
        this.state = this.getNoRaffleState();
        this.count = count;
    }

    public void deductMoney() {
        state.deductMoney(); // 調用目前的狀態對應的扣除方法
    }

    public void raffle() {
        if (state.raffle()) { // 如果抽獎成功
            state.dispensePrize();
        }
    }

    // 每領取一次，獎品數減少
    public int getCount() {
        int currentCount = count;
        count--;
        return currentCount;
    }
}
