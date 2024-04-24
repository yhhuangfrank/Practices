package com.frank.mytest.codetest.designpattern.proxy.staticproxy;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("teacher is teaching...");
    }
}
