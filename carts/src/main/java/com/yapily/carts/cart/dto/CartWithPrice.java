package com.yapily.carts.cart.dto;

import com.yapily.carts.cart.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartWithPrice {

    Cart cart;
    double total_cost;
}
