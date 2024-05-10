package com.frank.mytest.codetest.other;

import com.google.gson.Gson;
import lombok.ToString;

public class GsonTest {
    public static void main(String[] args) {

        Gson gson = new Gson();
        String json = "{\n" +
                "  \"pId\": 10,\n" +
                "  \"height\": 170.6,\n" +
                "  \"age\": 19,\n" +
                "  \"name\": \"Frank\"\n" +
                "}";
        MyClass myClass = gson.fromJson(json, MyClass.class);
        System.out.println(myClass);
    }
}

@ToString
class MyClass {
    private int pId;
    private Number height;
    private int age;
    private String name;
}
