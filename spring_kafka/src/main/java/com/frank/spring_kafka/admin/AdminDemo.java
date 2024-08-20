package com.frank.spring_kafka.admin;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;

public class AdminDemo {

    public static void main(String[] args) {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 管理者物件
        Admin admin = Admin.create(configMap);

        // 創建 topics
        NewTopic topic1 = new NewTopic("topic1", 1, (short) 1);

        admin.createTopics(List.of(topic1));

        admin.close();
    }

}
