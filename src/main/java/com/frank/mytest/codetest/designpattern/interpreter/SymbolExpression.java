package com.frank.mytest.codetest.designpattern.interpreter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 符號表達式，不實現 interpret 方法，由子類實現
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class SymbolExpression extends Expression{

    protected Expression left;
    protected Expression right;
}
