package com.frank.mytest.test.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoCollege implements College {
    private List<Department> departmentList;

    public InfoCollege() {
        departmentList = new ArrayList<>();
        addDepartment("資訊安全", "資訊安全");
        addDepartment("網路安全", "網路安全");
    }

    @Override
    public String getName() {
        return "資訊學院";
    }

    @Override
    public void addDepartment(String name, String des) {
        Department department = new Department(name, des);
        departmentList.add(department);
    }

    @Override
    public Iterator<Department> createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
