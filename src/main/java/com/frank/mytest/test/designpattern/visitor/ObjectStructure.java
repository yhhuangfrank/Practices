package com.frank.mytest.test.designpattern.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {
    // 維護一個集合
    private List<Person> persons = new LinkedList<>();

    public void attach(Person person) {
        persons.add(person);
    }

    public void detach(Person person) {
        persons.remove(person);
    }

    public void display(Action action) {
        for (Person person : persons) {
            person.accept(action);
        }
    }
}
