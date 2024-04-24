package com.frank.mytest.codetest.designpattern.decorator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Decorator extends Drink{
    private Drink drink; // 被裝飾的飲料

    @Override
    public float calculateCost() {
        // 取得自身價格 (getPrice()) + 組合的飲料價格做疊加
        return super.getPrice() + drink.calculateCost();
    }

    // 重寫 des，顯示出佐料加上飲料
    @Override
    public String getDes() {
        return super.getDes() + " " + super.getPrice() + " && " + drink.getDes();
    }
}
