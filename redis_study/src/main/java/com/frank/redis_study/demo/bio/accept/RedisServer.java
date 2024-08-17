package com.frank.redis_study.demo.bio.accept;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class RedisServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            System.out.println("模擬 RedisServer 啟動, 等待連接...");
            Socket socket = serverSocket.accept();
            System.out.println("成功連接 "+ UUID.randomUUID());
            System.out.println();
        }
    }
}
