package com.frank.mytest.codetest.designpattern.visitor;

public abstract class Action {

    // 得到男性評論
    public abstract void getManResult(Man man);

    // 得到女性評論
    public abstract void getWomanResult(Woman woman);
}
