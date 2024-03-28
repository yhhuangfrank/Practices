package com.frank.mytest.test.designpattern.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{

        String expression = getExpression();
        Map<String, Integer> valueMap = getValue(expression);
        Calculator calculator = new Calculator(expression);
        System.out.println("運算結果為： " + calculator.run(valueMap));
    }

    private static Map<String, Integer> getValue(String expression) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-') continue;

            if (!map.containsKey(String.valueOf(c))) {
                System.out.print("請輸入 " + c + " 的變量值: ");
                String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                map.put(String.valueOf(c), Integer.valueOf(in));
            }
        }

        scanner.close();
        return map;
    }

    private static String getExpression() throws IOException{
        System.out.print("請輸入表達式： ");
        return  (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }
}
