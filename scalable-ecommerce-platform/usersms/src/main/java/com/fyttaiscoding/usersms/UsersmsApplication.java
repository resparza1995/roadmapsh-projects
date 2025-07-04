package com.fyttaiscoding.usersms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fyttaiscoding.usersms.models.User;
import com.fyttaiscoding.usersms.services.UserService;

@SpringBootApplication
public class UsersmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersmsApplication.class, args);
	}

	@Bean
    CommandLineRunner loadData(UserService userService) {
        return args -> {
            userService.createUser(new User(1l, "User1", "user1@example.com"));
            userService.createUser(new User(2l, "User2", "user2@example.com"));
        };
    }

}
