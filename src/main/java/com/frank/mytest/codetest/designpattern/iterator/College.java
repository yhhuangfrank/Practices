package com.frank.mytest.codetest.designpattern.iterator;

import java.util.Iterator;

public interface College {

    String getName();


    void addDepartment(String name, String des);

    Iterator<Department> createIterator();
}
