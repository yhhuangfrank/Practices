package com.frank.redis_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedisScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisScheduleApplication.class, args);
	}

}
