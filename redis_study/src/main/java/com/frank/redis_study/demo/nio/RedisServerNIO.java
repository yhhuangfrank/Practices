package com.frank.redis_study.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisServerNIO {

    static List<SocketChannel> socketList = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {

        System.out.println("RedisServerNIO 啟動等待中...");
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 9999));
        serverSocket.configureBlocking(false); // 設置非阻塞

        while (true) {
            // 使用輪詢替代阻塞
            for (SocketChannel socket : socketList) {
                int read = socket.read(byteBuffer);
                if (read > 0) {
                    System.out.println("==== 讀取數據： " + read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes));
                    byteBuffer.clear();
                }
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("======成功連接!");
                socketChannel.configureBlocking(false);
                socketList.add(socketChannel);
                System.out.println("socket list size : " + socketList.size());
            }
        }
    }
}
