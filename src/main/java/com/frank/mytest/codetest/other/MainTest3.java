package com.frank.mytest.codetest.other;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class MainTest3 {
    public static void main(String[] args) {

        String s = "a";
        System.out.println(switchTest(s));

        System.out.println("a01".compareToIgnoreCase("A01"));

        Person p1 = new Person("a01", "frank");
        Person p2 = new Person("A01", "frank");
        Person p3 = new Person("A02", "frank");

//        System.out.println(sortPersons(new ArrayList<>(List.of(p1, p2, p3))));

        List<String> strings = new ArrayList<>(List.of("a02", "A01", "a01", "B01", "c01", "132", "Aa01", "aa01"));
        List<String> sorted = strings.stream().distinct().sorted(String::compareTo).toList();
        System.out.println(sorted);
        System.out.println(strings);
    }

    public static String switchTest(String string) {
        return switch (string) {
            case "a" -> "a";
            case "b" -> "b";
            default -> "c";
        };
    }

    public static List<Person> sortPersons(List<Person> people) {
        people.sort((p1, p2) -> p1.id.compareToIgnoreCase(p2.id));
        return people;
    }

    @ToString
    static class Person {
        private String id;
        private String name;

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
