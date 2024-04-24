package com.frank.mytest.codetest.designpattern.interpreter;

import java.util.Map;
import java.util.Stack;

public class Calculator {

    // 定義表達式
    private Expression expression;

    // 建構子傳參數，並解析
    public Calculator(String expressionString) {// ex: a+b-c
        // 安排運算先後順序
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expressionString.toCharArray();

        Expression left;
        Expression right;

        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+' -> {
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[i + 1]));
                    stack.push(new AddExpression(left, right));
                }
                case '-' -> {
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[i + 1]));
                    stack.push(new SubExpression(left, right));
                }
                default -> {
                    if (i != charArray.length - 1) {
                        stack.push(new VarExpression(String.valueOf(charArray[i])));
                    }
                }
            }
        }
        // 將最後解析完的表達式賦予
        this.expression = stack.pop();
    }

    // 給予變量 map, ex: {a=10,b=20}
    public int run (Map<String, Integer> var) {
        // 將表達式 a+b-c 和 var 綁定
        return this.expression.interpret(var);
    }
}
