package com.frank.mytest.codetest.designpattern.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    private String type;
    private String area;
}
