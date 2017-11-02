package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan("com.example")
@EnableMongoRepositories("com.example.repository")
@SpringBootApplication
public class FacebookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookAppApplication.class, args);
	}
}
