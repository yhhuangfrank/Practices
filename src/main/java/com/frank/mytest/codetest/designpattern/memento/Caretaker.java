package com.frank.mytest.codetest.designpattern.memento;

import lombok.Getter;
import lombok.Setter;

// 保存狀態之物件
@Getter
@Setter
public class Caretaker {

    private Memento memento; // 單個狀態

    // private List<Memento> mementoList; // 保存多個狀態
    // private Map<String, Memento> rolesMemento; // 針對不同角色保存各自的狀態

}
