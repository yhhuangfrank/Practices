package com.frank.mytest.test.designpattern.iterator;

import lombok.AllArgsConstructor;

import java.util.Iterator;

public class EngineeringCollegeIterator implements Iterator<Department> {
    // 明確指定存放的結構
    private Department[] departments;
    private int position = 0;

    public EngineeringCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        return position < departments.length && departments[position] != null;
    }

    @Override
    public Department next() {
        Department department = departments[position];
        position++;
        return department;
    }
}
