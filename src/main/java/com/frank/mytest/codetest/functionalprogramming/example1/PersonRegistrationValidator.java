package com.frank.mytest.codetest.functionalprogramming.example1;

import java.util.function.Function;
import com.frank.mytest.codetest.functionalprogramming.example1.PersonRegistrationValidator.ValidationResult;
import lombok.Getter;

import static com.frank.mytest.codetest.functionalprogramming.example1.PersonRegistrationValidator.ValidationResult.*;

public interface PersonRegistrationValidator extends Function<Person, ValidationResult> {

    static PersonRegistrationValidator isEmailValid() {
        return person -> {
            System.out.println("check email valid");
            return person.email().contains("@") ? SUCCESS : EMAIL_NOT_VALID_ERROR;
        };
    }

    static PersonRegistrationValidator isAgeValid() {
        return person -> {
            System.out.println("check age valid");
            return person.age() > 0 ? SUCCESS : AGE_ERROR;
        };
    }

    static PersonRegistrationValidator isEmailTaken() {
        return person -> {
            System.out.println("check email taken");
            return person.email().equals("frank@mail.com") ? EMAIL_TAKEN_ERROR : SUCCESS;
        };
    }

    default PersonRegistrationValidator and(PersonRegistrationValidator nextValidator) {
        return person -> {
            ValidationResult result = this.apply(person); // 確認目前條件
            // 目前條件成功才繼續判斷後續
            return SUCCESS.equals(result) ? nextValidator.apply(person) : result;
        };
    }

    @Getter
    enum ValidationResult {
        SUCCESS("success!"),
        EMAIL_NOT_VALID_ERROR("email format is not valid!"),
        EMAIL_TAKEN_ERROR("current email is already taken!"),
        AGE_ERROR("age is not valid!");

        private final String message;

        ValidationResult(String message) {
            this.message = message;
        }
    }
}
