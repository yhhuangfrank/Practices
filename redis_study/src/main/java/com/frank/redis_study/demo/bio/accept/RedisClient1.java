package com.frank.redis_study.demo.bio.accept;

import java.io.IOException;
import java.net.Socket;

public class RedisClient1 {

    public static void main(String[] args) throws IOException {

        System.out.println("RedisClient1 start ...");
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println("RedisClient1 connection end ...");
    }

}
