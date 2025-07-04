package com.fyttaiscoding.shoppingcartms.repositories;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import com.fyttaiscoding.shoppingcartms.models.ShoppingCart;

@Repository
public class InMemoryShoppingCartRepository implements IShoppingCartRepository {
    private final Map<Long, ShoppingCart> store = new ConcurrentHashMap<>();

    @Override
    public Optional<ShoppingCart> findByUserId(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public void save(ShoppingCart cart) {
        store.put(cart.getUserId(), cart);
    }

    @Override
    public void deleteByUserId(Long userId) {
        store.remove(userId);
    }
}
