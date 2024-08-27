package com.yapily.carts.cart;

import com.yapily.carts.cart.dto.CartWithPrice;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    Cart createCart();
    Cart getByCartId(Long id);
    boolean deleteByCartId(Long id);
    Cart updateCart(Long id, CartProduct[] updatedCart);

    CartWithPrice checkout(Long id);
}
