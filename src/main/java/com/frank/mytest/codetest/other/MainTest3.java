package com.frank.mytest.codetest.other;

public class MainTest3 {
    public static void main(String[] args) {

        String s = "a";
        System.out.println(switchTest(s));

    }

    public static String switchTest(String string) {
        return switch (string) {
            case "a" -> "a";
            case "b" -> "b";
            default -> "c";
        };
    }
}
