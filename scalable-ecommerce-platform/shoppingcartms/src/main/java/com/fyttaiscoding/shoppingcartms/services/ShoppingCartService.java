package com.fyttaiscoding.shoppingcartms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fyttaiscoding.shoppingcartms.models.CartItem;
import com.fyttaiscoding.shoppingcartms.models.ShoppingCart;
import com.fyttaiscoding.shoppingcartms.repositories.IShoppingCartRepository;

@Service
public class ShoppingCartService {
    private final IShoppingCartRepository repository;

    public ShoppingCartService(IShoppingCartRepository repository) {
        this.repository = repository;
    }

    public ShoppingCart addItem(Long userId, CartItem item) {
        ShoppingCart cart = repository.findByUserId(userId)
            .orElse(new ShoppingCart(userId, new java.util.ArrayList<>()));
        cart.getItems().removeIf(i -> i.getProductId().equals(item.getProductId()));
        cart.getItems().add(item);
        repository.save(cart);
        return cart;
    }

    public ShoppingCart removeItem(Long userId, String productId) {
        ShoppingCart cart = repository.findByUserId(userId)
            .orElse(new ShoppingCart(userId, new java.util.ArrayList<>()));
        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        repository.save(cart);
        return cart;
    }

    public Optional<ShoppingCart> getCart(Long userId) {
        return repository.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        repository.deleteByUserId(userId);
    }
}
