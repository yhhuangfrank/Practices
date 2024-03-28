package com.frank.mytest.test.designpattern.iterator;

import java.util.Iterator;

public class EngineeringCollege implements College {
    private Department[] departments;
    private int numOfDepartments; // 保存目前 array 物件個數

    public EngineeringCollege() {
        departments = new Department[5];
        addDepartment("mse", "material science");
        addDepartment("cs", "computer science");
        addDepartment("ee", "electric engineering");
    }

    @Override
    public String getName() {
        return "工學院";
    }

    @Override
    public void addDepartment(String name, String des) {
        Department department = new Department(name, des);
        departments[numOfDepartments] = department;
        numOfDepartments++;
    }

    @Override
    public Iterator<Department> createIterator() {
        return new EngineeringCollegeIterator(departments);
    }
}
