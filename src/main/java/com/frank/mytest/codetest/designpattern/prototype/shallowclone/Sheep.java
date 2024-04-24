package com.frank.mytest.codetest.designpattern.prototype.shallowclone;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sheep implements Cloneable { // 實現 cloneable 做屬性複製
    private String name;
    private int age;
    private String color;

    // 複寫 Object.clone 方法
    @Override
    protected Sheep clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }

        return sheep;
    }
}
