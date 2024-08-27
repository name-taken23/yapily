package com.yapily.carts.cart.impl;

import com.yapily.carts.cart.*;
import com.yapily.carts.cart.CartProduct;
import com.yapily.carts.cart.dto.CartWithPrice;
import com.yapily.carts.cart.external.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    CartProductRepository cartProductRepository;

    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getByCartId(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteByCartId(Long id) {
        try {
            cartRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Cart updateCart(Long id, CartProduct[] updatedCart) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = cartOptional.get();

        if (!cartOptional.get().getChecked_out()) {
            RestTemplate restTemplate = new RestTemplate();
            List<CartProduct> all = cartProductRepository.findAllByCart_Id(cart.getCart_id());

            Product product;
            //iterate through each product in new cart

            for (CartProduct cartProduct : updatedCart) {
                if (all.size() == 0) {
                    product = restTemplate.getForObject("http://localhost:8081/products/" + cartProduct.getProduct_id(), Product.class);
                    if (product != null) {
                        CartProduct _cartProduct = new CartProduct(cart.getCart_id(), product.getProduct_id(), cartProduct.getQuantity());
                        cartProductRepository.save(_cartProduct);
                    }
                }
                for (CartProduct cartProduct1 : all) {
                    if (cartProduct.getProduct_id() == cartProduct1.getProduct_id()) {
                        CartProduct cp2 = cartProductRepository.findByCartIdProductId(cartProduct1.getCart_id(), cartProduct1.getProduct_id());
                        cp2.setQuantity(cartProduct.getQuantity());
                        cartProductRepository.save(cp2);
                    } else {
                        product = restTemplate.getForObject("http://localhost:8081/products/" + cartProduct.getProduct_id(), Product.class);
                        if (product != null) {
                            CartProduct _cartProduct = new CartProduct(cart.getCart_id(), product.getProduct_id(), cartProduct.getQuantity());
                            cartProductRepository.save(_cartProduct);
                        }
                    }
                }
            }
            List<CartProduct> newCart = cartProductRepository.findAllByCart_Id(cart.getCart_id());
            CartProduct[] newCartArr = newCart.toArray(new CartProduct[0]);
            cart.setProducts(newCartArr);
            cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public CartWithPrice checkout(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        Cart cart = cartOptional.get();
        RestTemplate restTemplate = new RestTemplate();
        Product product;
        double price = 0;
        CartWithPrice cartWithPrice = new CartWithPrice();
        for (CartProduct cp : cart.getProducts()) {
            product = restTemplate.getForObject("http://localhost:8081/products/" + cp.getProduct_id(), Product.class);
            if (product != null) {
                price += product.getPrice();
            }
        }
        cart.setChecked_out(true);
        cartRepository.save(cart);
        cartWithPrice.setCart(cart);
        cartWithPrice.setTotal_cost(price);
        return cartWithPrice;
    }
}
