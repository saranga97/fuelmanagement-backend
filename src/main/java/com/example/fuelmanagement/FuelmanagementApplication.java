package com.example.fuelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FuelmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelmanagementApplication.class, args);
	}

}
