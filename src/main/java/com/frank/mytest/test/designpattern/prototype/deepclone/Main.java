package com.frank.mytest.test.designpattern.prototype.deepclone;

public class Main {
    public static void main(String[] args) {
        System.out.println("深拷貝");

        Sheep sheep = new Sheep("frank");
        sheep.setFriend(new Horse("jack"));

        // 方式 1 : 使用 clone
        Sheep sheep1 = (Sheep) sheep.clone();

        System.out.println(sheep + ", sheep.friend.hashcode: " + sheep.getFriend().hashCode());
        System.out.println(sheep1 + ", sheep1.friend.hashcode: " + sheep1.getFriend().hashCode());

//        方式 2 : 使用序列化
        sheep = new Sheep("frank1");
        sheep.setFriend(new Horse("jack1"));

        Sheep sheep2 = (Sheep) sheep.deepClone();

        System.out.println(sheep + ", sheep.friend.hashcode: " + sheep.getFriend().hashCode());
        System.out.println(sheep2 + ", sheep2.friend.hashcode: " + sheep2.getFriend().hashCode());
    }
}
