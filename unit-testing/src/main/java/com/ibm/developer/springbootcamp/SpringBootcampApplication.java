package com.ibm.developer.springbootcamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootcampApplication.class, args);
	}

	@Bean
	public List<User> userPersistence() {
		return new ArrayList<User>();
	}
}
