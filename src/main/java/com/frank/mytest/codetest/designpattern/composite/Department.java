package com.frank.mytest.codetest.designpattern.composite;

// 系，由於是最小組織單位，因此不需重寫 add, remove 方法
public class Department extends Organization{

    public Department(String name, String des) {
        super(name, des);
    }

    @Override
    protected void print() {
        System.out.println(getName() + ": " + getDes());
    }
}
