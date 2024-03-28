package com.frank.mytest.test.designpattern.memento;

public class Main {

    public static void main(String[] args) {

        GameRole gameRole = new GameRole();
        gameRole.setAttack(100);
        gameRole.setDefense(100);

        System.out.println("第一次狀態：");
        gameRole.display();

        // 保存到 caretaker
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameRole.createMemento());

        System.out.println("第二次狀態：");
        gameRole.setAttack(30);
        gameRole.setDefense(30);
        gameRole.display();

        // 使用備忘錄恢復狀態
        gameRole.recoverFromMemento(caretaker.getMemento());
        gameRole.display();
    }
}
