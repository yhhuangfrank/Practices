package com.frank.mytest.codetest.designpattern.proxy.staticproxy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeacherDaoProxy implements ITeacherDao{
    private ITeacherDao target; // 目標物件，透過介面來聚合

    @Override
    public void teach() {
        System.out.println("開始代理...");
        target.teach();
        System.out.println("結束代理...");
    }
}
