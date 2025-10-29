package com.example.club.sportif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.club.sportif")
public class ClubSportifApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubSportifApplication.class, args);
	}

}
