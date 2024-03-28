package com.frank.mytest.test.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<College> colleges = new ArrayList<>();

        College engineeringCollege = new EngineeringCollege();
        College infoCollege = new InfoCollege();

        colleges.add(engineeringCollege);
        colleges.add(infoCollege);

        University university = new University(colleges);
        university.printCollegeList();

    }
}
