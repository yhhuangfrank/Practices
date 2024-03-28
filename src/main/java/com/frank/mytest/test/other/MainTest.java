package com.frank.mytest.test.other;

public class MainTest {
    public static void main(String[] args) {
//        Emp2 emp2 = new Emp2();
//        emp2.setName("Frank");
//
//        Emp1 emp1 = (Emp1) emp2;
//        System.out.println(emp1.getName());

        Integer a = null;
        Integer b = null;

        m1(a, b);
    }

    public static void m1(int a, int b) {
        int compare = Integer.valueOf(a).compareTo(Integer.valueOf(b));
        System.out.println(compare);
        System.out.println(a);
        System.out.println(b);
    }
}
