package com.yapily.cart.controller;

import com.yapily.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/carts")
public class CartController {

    @Autowired
    CartRepository cartRepository;
}
