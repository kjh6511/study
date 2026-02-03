package com.mealgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MealgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealgoApplication.class, args);
	}

}
