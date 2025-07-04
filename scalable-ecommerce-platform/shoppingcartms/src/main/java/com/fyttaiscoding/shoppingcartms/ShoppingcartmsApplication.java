package com.fyttaiscoding.shoppingcartms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fyttaiscoding.shoppingcartms.models.CartItem;
import com.fyttaiscoding.shoppingcartms.services.ShoppingCartService;

@SpringBootApplication
public class ShoppingcartmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartmsApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(ShoppingCartService cartService) {
        return args -> {
            cartService.addItem(1l, new CartItem("prod1", 2));
            cartService.addItem(1l, new CartItem("prod2", 1));

            cartService.addItem(2l, new CartItem("prod3", 3));
        };
    }

}
