package com.frank.mytest.test.designpattern.bridge;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        // 新建手機 (樣式 + 品牌)
        Phone phone = new FoldedPhone(new Android());

        phone.open();
        phone.call();
        phone.close();

        Phone phone1 = new FoldedPhone(new Apple());

        phone1.open();
        phone1.call();
        phone1.close();
    }
}
