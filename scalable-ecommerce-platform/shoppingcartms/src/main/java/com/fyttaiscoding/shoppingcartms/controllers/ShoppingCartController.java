package com.fyttaiscoding.shoppingcartms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyttaiscoding.shoppingcartms.models.CartItem;
import com.fyttaiscoding.shoppingcartms.models.ShoppingCart;
import com.fyttaiscoding.shoppingcartms.services.ShoppingCartService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<ShoppingCart> addItem(
            @PathVariable Long userId,
            @RequestBody CartItem item) {
        return ResponseEntity.ok(service.addItem(userId, item));
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<ShoppingCart> removeItem(
            @PathVariable Long userId,
            @PathVariable String productId) {
        return ResponseEntity.ok(service.removeItem(userId, productId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long userId) {
        return service.getCart(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        service.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
