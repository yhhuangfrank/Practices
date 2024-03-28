package com.frank.mytest.test.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

// 大學
public class University extends Organization{

    List<Organization> organizationList = new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }

    // 重寫 add 方法
    @Override
    protected void add(Organization organization) {
        organizationList.add(organization);
    }

    // 重寫 remove 方法
    @Override
    protected void remove(Organization organization) {
        organizationList.remove(organization);
    }

    @Override
    protected void print() {
        System.out.println("=========" + getName() + "=========");
        for (Organization organization : organizationList) {
            organization.print();
        }
    }
}
