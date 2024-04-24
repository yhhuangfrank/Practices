package com.frank.mytest.codetest.designpattern.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameRole {
    private int attack;
    private int defense;

    // 創建 Memento，即根據目前狀態得到 Memento
    public Memento createMemento() {
        return new Memento(attack, defense);
    }

    // 從備忘錄物件，恢復 GameRole 狀態
    public void recoverFromMemento(Memento memento) {
        this.attack = memento.getAttack();
        this.defense = memento.getDefense();
    }

    // 顯示目前狀態
    public void display() {
        System.out.println("目前角色 attack: " + this.attack + " , def: " + this.defense);
    }
}
