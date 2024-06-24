package com.frank.mytest.codetest.functionalprogramming.example1;

import static com.frank.mytest.codetest.functionalprogramming.example1.PersonRegistrationValidator.ValidationResult.SUCCESS;

public class Main {

    public static void main(String[] args) {

        Person p1 = new Person("frank@mail.com", 38);
        Person p2 = new Person("jack@mail.com", -1);
        Person p3 = new Person("wendy@mail.com", 18);

        PersonRegistrationValidator validator = PersonRegistrationValidator.isEmailTaken()
                .and(PersonRegistrationValidator.isEmailValid())
                .and(PersonRegistrationValidator.isAgeValid());

        PersonRegistrationValidator.ValidationResult res1 = validator.apply(p1);
        System.out.println(res1);
        System.out.println("=====================");
        PersonRegistrationValidator.ValidationResult res2 = validator.apply(p2);
        System.out.println(res2);
        System.out.println("=====================");
        PersonRegistrationValidator.ValidationResult res3 = validator.apply(p3);
        System.out.println(res3);

        if (!SUCCESS.equals(res1)) {
            System.out.println(res1.getMessage());
        }
        if (!SUCCESS.equals(res2)) {
            System.out.println(res2.getMessage());
        }
        if (!SUCCESS.equals(res3)) {
            System.out.println(res3.getMessage());
        }
    }

}
