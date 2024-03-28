package com.frank.mytest;

import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void test1() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        System.out.println("m = " + m());
    }

    private long m() {
        long result = 0;
        try {
           result =  m1();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        return result;
    }

    private long m1() throws RuntimeException {
        throw new RuntimeException();
    }
}
