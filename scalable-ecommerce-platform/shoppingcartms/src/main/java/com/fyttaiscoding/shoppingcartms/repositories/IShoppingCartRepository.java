package com.fyttaiscoding.shoppingcartms.repositories;

import com.fyttaiscoding.shoppingcartms.models.ShoppingCart;
import java.util.Optional;

public interface IShoppingCartRepository {
    Optional<ShoppingCart> findByUserId(Long userId);
    void save(ShoppingCart cart);
    void deleteByUserId(Long userId);
}
