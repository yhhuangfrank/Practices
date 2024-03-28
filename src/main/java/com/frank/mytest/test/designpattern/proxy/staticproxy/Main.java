package com.frank.mytest.test.designpattern.proxy.staticproxy;

public class Main {
    public static void main(String[] args) {
        // 創建目標物件 (被代理物件)
        TeacherDao teacherDao = new TeacherDao();

        // 創建代理物件，同時將被代理物件傳遞給代理物件
        TeacherDaoProxy proxy = new TeacherDaoProxy(teacherDao);

        // 透過代理物件調用
        proxy.teach();

    }
}
