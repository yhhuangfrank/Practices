package com.frank.mytest.codetest.designPattern2;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Strategy {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Doe", 20, false),
                new Person("Smith", 30, true),
                new Person("Old", 70, true)
        );

        PeopleCounter counter = new PeopleCounter();

        counter.setFilter(new AdultFilter());
        System.out.println(counter.count(people)); // Adult count: 3

        counter.setFilter(new SeniorFilter());
        System.out.println(counter.count(people)); // Senior count: 1

        counter.setFilter(new MarriedFilter());
        System.out.println(counter.count(people)); // Married count: 2


    }
}

@Getter
class Person {
    private String lastName;
    private int age;
    private boolean isMarried;

    public Person(String lastName, int age, boolean isMarried) {
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
    }
}

interface PersonFilter {
    boolean apply(Person person);
}

class AdultFilter implements PersonFilter {
    // Implement Adult filter
    @Override
    public boolean apply(Person person) {
        return person.getAge() >= 18;
    }
}

class SeniorFilter implements PersonFilter {
    // Implement Senior filter
    @Override
    public boolean apply(Person person) {
        return person.getAge() >= 65;
    }

}

class MarriedFilter implements PersonFilter {
    // Implement Married filter
    @Override
    public boolean apply(Person person) {
        return person.isMarried();
    }

}

class PeopleCounter {
    private PersonFilter filter;

    public void setFilter(PersonFilter filter) {
        this.filter = filter;
    }

    public int count(List<Person> people) {
        // Implement method here
        int num = 0;
        for (Person person : people) {
            if (this.filter.apply(person)) {
                num += 1;
            }
        }
        return num;
    }
}

