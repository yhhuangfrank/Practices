package com.frank.mytest.codetest.designpattern.iterator;

import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class University {
    private List<College> collegeList;

    private void printDepartment(Iterator<Department> iterator) {
        while (iterator.hasNext()) {
            Department d = iterator.next();
            System.out.println(d.getName());
        }
    }

    public void printCollegeList() {
        Iterator<College> iterator = collegeList.iterator();

        while (iterator.hasNext()) {
            College c = iterator.next();
            System.out.println("學院：" + c.getName());
            printDepartment(c.createIterator());
        }
    }
}
