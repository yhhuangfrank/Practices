package com.frank.mytest.test.designpattern.command;

public interface Command {
    void execute(); // 執行某個操作
    void undo(); // 撤銷
}
