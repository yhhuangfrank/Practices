package com.frank.mytest.test.designpattern.composite;

public class Main {
    public static void main(String[] args) {

        Organization university = new University("NTHU", "國立清華大學");

        Organization engineeringCollege = new College("College of Engineering", "工學院");
        Organization college = new College("理學院", "理學院");

        engineeringCollege.add(new Department("MSE", "材料科學系"));
        engineeringCollege.add(new Department("CS", "電腦科學系"));
        college.add(new Department("Chemistry", "化學系"));

        university.add(engineeringCollege);
        university.add(college);

        university.print();
        engineeringCollege.print();
        college.print();
    }
}
