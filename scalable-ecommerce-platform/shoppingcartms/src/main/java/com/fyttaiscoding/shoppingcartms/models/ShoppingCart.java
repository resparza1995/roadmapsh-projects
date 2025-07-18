package com.fyttaiscoding.shoppingcartms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private Long userId;
    private List<CartItem> items = new ArrayList<>();
}
