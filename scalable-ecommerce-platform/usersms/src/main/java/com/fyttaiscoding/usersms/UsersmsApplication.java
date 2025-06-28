package com.fyttaiscoding.usersms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fyttaiscoding.usersms.services.UserService;

@SpringBootApplication
public class UsersmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersmsApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(UserService userService) {
        return args -> {
            userService.initializeSampleUsers();
        };
    }

}
