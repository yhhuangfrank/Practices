package com.frank.mytest.test.designpattern.interpreter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 變量表達式
 */
@NoArgsConstructor
@AllArgsConstructor
public class VarExpression extends Expression{
    private String key;

    @Override
    public int interpret(Map<String, Integer> var) {
        return var.get(this.key);
    }
}
