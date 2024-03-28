package com.frank.mytest.test.designpattern.command;

public class Main {
    public static void main(String[] args) {


        // 創建接收者
        LightReceiver receiver = new LightReceiver();

        // 創建相關命令
        LightOnCommand lightOnCommand = new LightOnCommand(receiver);
        LightOffCommand lightOffCommand = new LightOffCommand(receiver);

        // 遙控器
        RemoteController remoteController = new RemoteController();

        // 給遙控器設置命令，no = 0 是對電燈的操作
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        System.out.println("=======按下開電燈=======");
        remoteController.onOpen(0);
        System.out.println("=======按下關電燈=======");
        remoteController.onClose(0);
        System.out.println("=======按下撤銷=======");
        remoteController.onUndo();
    }
}
