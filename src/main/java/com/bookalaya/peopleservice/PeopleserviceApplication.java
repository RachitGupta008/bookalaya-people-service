package com.bookalaya.peopleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.bookalaya.peopleservice.repository")
public class PeopleserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleserviceApplication.class, args);
	}

}
