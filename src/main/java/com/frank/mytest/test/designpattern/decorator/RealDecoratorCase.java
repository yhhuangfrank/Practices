package com.frank.mytest.test.designpattern.decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;

// jdk 中 io 即體現裝飾者模式
public class RealDecoratorCase {
    public static void main(String[] args) throws Exception{

        // InputStream 是抽象類，類似 Drink
        // FileInputStream 是 InputStream 的子類，類似 LongBlack
        // FilterInputStream 是 InputStream 的子類，類似 Decorator (修飾者)
        // DataInputStream 是 FilterInputStream 的子類，類似 Milk (具體修飾者)
        // FilterInputStream 類中有 protected volatile InputStream in; 即含有被裝飾者

        DataInputStream dis = new DataInputStream(new FileInputStream("/abc.txt"));
    }
}
