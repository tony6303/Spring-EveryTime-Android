package com.cos.everytimeandroid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EverytimeAndroidApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverytimeAndroidApplication.class, args);
	}

}
