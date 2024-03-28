package com.frank.mytest.test.designpattern.proxy.dynamic;

public class Main {
    public static void main(String[] args) {

        ITeacherDao teacherDao = new TeacherDao();

        ITeacherDao proxy = (ITeacherDao) new ProxyFactory(teacherDao).getProxyInstance();

        System.out.println(proxy);
        proxy.teach();
    }
}
