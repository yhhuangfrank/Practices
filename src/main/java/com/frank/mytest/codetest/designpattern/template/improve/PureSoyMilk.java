package com.frank.mytest.codetest.designpattern.template.improve;

// 純豆漿
public class PureSoyMilk extends SoyMilk{

    @Override
    void addCondiments() {

    }

    // 覆寫方法
    @Override
    boolean needCondiments() {
        System.out.println("不需配料");
        return false;
    }
}
