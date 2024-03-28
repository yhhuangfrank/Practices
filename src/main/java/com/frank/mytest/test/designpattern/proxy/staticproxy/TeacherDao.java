package com.frank.mytest.test.designpattern.proxy.staticproxy;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("teacher is teaching...");
    }
}
