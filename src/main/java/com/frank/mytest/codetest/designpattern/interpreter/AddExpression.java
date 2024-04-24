package com.frank.mytest.codetest.designpattern.interpreter;

import java.util.Map;

public class AddExpression extends SymbolExpression{
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> var) {
        // super.left.interpret(var) 返回左表達式對應的值
        return super.left.interpret(var) + super.right.interpret(var);
    }
}
