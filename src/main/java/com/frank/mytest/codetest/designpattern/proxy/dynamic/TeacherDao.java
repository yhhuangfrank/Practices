package com.frank.mytest.codetest.designpattern.proxy.dynamic;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("dynamic teacher");
    }
}
