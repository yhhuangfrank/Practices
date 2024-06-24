package com.frank.mytest.codetest.functionalprogramming.example2;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        check("frank", null, cb);
        check("naruto", "uzumaki", cb);
    }

    static Consumer<String> cb = input -> System.out.println(input + ", you need have lastName");

    static void check(String firstName, String lastName, Consumer<String> cb) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            cb.accept(firstName);
        }
    }
}
