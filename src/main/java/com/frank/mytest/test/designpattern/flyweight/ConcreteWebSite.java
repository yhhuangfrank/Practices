package com.frank.mytest.test.designpattern.flyweight;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ConcreteWebSite extends WebSite{
    private String type = ""; // 網站發布形式
    @Override
    public void use(User user) { // User 為外部屬性
        System.out.println("網站發布形式 " + type + ", user name :" + user.getName());
    }
}
