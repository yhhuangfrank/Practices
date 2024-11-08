package com.frank.mytest.codetest.other;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
//        Integer a = null;
//        Integer b = null;
//
//        m1(a, b);

//        test();
        
//        test2();

        test3();
    }

    public static void m1(int a, int b) {
        int compare = Integer.valueOf(a).compareTo(Integer.valueOf(b));
        System.out.println(compare);
        System.out.println(a);
        System.out.println(b);
    }

    public static void test() {
        String json = "{'value': false, 'name': 'frank'}";
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.get("value"));
        System.out.println(jsonObject.getString("value"));
    }

    public static void test2() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> list1 = list.stream().filter(i -> i == 4).toList();
        System.out.println(list1);
    }

    public static void test3() {
        String string  = "g1";
        String[] arr = string.split(",");
        System.out.println(Arrays.toString(arr));
    }
}
