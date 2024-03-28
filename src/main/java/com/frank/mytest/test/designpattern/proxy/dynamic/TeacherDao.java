package com.frank.mytest.test.designpattern.proxy.dynamic;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("dynamic teacher");
    }
}
