package com.frank.mytest.test.concurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

// 內建一個 user 類
@Getter
@ToString
@AllArgsConstructor
class User {
    private String name;
    private int age;
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User frank = new User("frank", 18);
        User wendy = new User("wendy", 16);
        atomicReference.set(frank); // 先對原子類別放入 frank

        System.out.println(atomicReference.compareAndSet(frank, wendy) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(frank, wendy) + "\t" + atomicReference.get().toString());

    }
}
