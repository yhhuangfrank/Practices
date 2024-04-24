package com.frank.mytest.codetest.designpattern.template.improve;

public class Main {

    public static void main(String[] args) {

        // 製作紅豆豆漿
        System.out.println("===製作紅豆豆漿===");
        SoyMilk redBeanSoyMilk = new RedBeanSoyMilk();
        redBeanSoyMilk.make();

        System.out.println("===製作花生豆漿===");
        SoyMilk peanutSoyMilk = new PeanutSoyMilk();
        peanutSoyMilk.make();

        System.out.println("===製作純豆漿===");
        SoyMilk pureSoyMilk = new PureSoyMilk();
        pureSoyMilk.make();
    }
}
