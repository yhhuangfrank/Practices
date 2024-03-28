package com.frank.mytest.test.designpattern.interpreter;

import java.util.Map;

/**
 * 抽象類表達式，透過 map key-value 可以獲取到變量的值
 * ex: a + b - c
 * interpret 方法解釋公式和數值
 * key 為公式(表達式), 參數[a,b,c]
 * value 為具體值
 */
public abstract class Expression {

    public abstract int interpret(Map<String, Integer> var);
}
