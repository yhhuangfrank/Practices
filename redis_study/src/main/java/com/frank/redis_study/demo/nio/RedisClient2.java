package com.frank.redis_study.demo.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * non-blocking
 */
public class RedisClient2 {

    public static void main(String[] args) throws IOException {

        System.out.println("RedisClient1 start...");
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            if (str.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(str.getBytes());
            System.out.println("finish write input and type quit to exit...");
        }
        outputStream.close();
        socket.close();

    }
}
