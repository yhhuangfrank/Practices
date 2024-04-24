package com.frank.mytest.codetest.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

public class SynchronizedUpDemo {

    public static void main(String[] args) {

        Object o = new Object();

//        System.out.println("10 進制 " + o.hashCode());
//        System.out.println("16 進制 " + Integer.toHexString(o.hashCode()));
//        System.out.println("2 進制 " + Integer.toBinaryString(o.hashCode()));

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

    }
}
