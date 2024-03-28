package com.frank.mytest.test.designpattern.command;

// 沒有任何命令，即空執行：用於初始化每個按鈕，當調用空命令時，什麼事都不做
// 省去對空的判斷
public class NoCommand implements Command {
    @Override
    public void execute() {
        // default nothing to do
    }

    @Override
    public void undo() {
        // default nothing to do
    }
}
