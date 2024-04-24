package com.frank.mytest.codetest.designpattern.flyweight;

public class FlyweightRealCase {
    public static void main(String[] args) {

        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);

        System.out.println(x.equals(y)); // 比大小，true
        System.out.println(x == y); // false
        System.out.println(x == z); // true，因底層會針對 -128 ~ 127 ，使用 cache
        System.out.println(x == w); // false
        System.out.println(w == y);// false

        Integer x1 = Integer.valueOf(128);
        Integer x2 = Integer.valueOf(128);
        System.out.println("x1 == x2: " + (x1 == x2));
    }
}
