package com.frank.redis_study.demo.bio.read;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class RedisServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            System.out.println("等待連接1....");
            Socket socket = serverSocket.accept(); // 阻塞 1
            System.out.println("等待連接2....");

            InputStream inputStream = socket.getInputStream();
            int length;
            byte[] bytes = new byte[1024];
            System.out.println("等待讀取．．．");
            while ((length = inputStream.read(bytes)) != -1) { // 阻塞 2, 等待客戶端發送數據
                System.out.println("成功讀取 : " + new String(bytes, 0, length));
                System.out.println("成功連接 "+ UUID.randomUUID());
                System.out.println();
            }
            inputStream.close();
            socket.close();
        }
    }
}
