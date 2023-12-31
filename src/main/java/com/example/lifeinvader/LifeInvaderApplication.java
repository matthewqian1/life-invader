package com.example.lifeinvader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LifeInvaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeInvaderApplication.class, args);
	}

}
