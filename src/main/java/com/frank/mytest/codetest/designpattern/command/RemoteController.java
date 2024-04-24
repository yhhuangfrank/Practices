package com.frank.mytest.codetest.designpattern.command;

public class RemoteController {
    private Command[] onCommands;   // 開按鈕的命令 array
    private Command[] offCommands; // 關閉命令
    private Command undoCommand; // 撤銷命令

    // 初始化按鈕
    public RemoteController() {
        onCommands= new Command[5];
        offCommands= new Command[5];

        for (int i = 0; i <5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    // 按下開按鈕
    public void onOpen(int no) {
        onCommands[no].execute();
        // 紀錄此次操作，用於撤銷
        undoCommand = onCommands[no];
    }

    // 按下關按鈕
    public void onClose(int no) {
        offCommands[no].execute();
        // 紀錄此次操作，用於撤銷
        undoCommand = offCommands[no];
    }

    public void onUndo() {
        undoCommand.undo();
    }
}
