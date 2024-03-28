package com.frank.mytest.test.designpattern.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Organization {
    private String name;
    private String des;

    protected void add(Organization organization) {
        // 預設實現
        throw new UnsupportedOperationException();
    }

    protected void remove(Organization organization) {
        // 預設實現
        throw new UnsupportedOperationException();
    }

    // 需要子類實現
    protected abstract void print();
}
