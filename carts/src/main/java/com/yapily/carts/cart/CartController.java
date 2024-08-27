package com.yapily.carts.cart;

import com.yapily.carts.cart.dto.CartWithPrice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carts")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getByCartId(@PathVariable Long id) {
        Cart cart = cartService.getByCartId(id);
        if (cart != null)
            return new ResponseEntity<>(cart, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody CartProduct[] updatedCart) {
        Cart cart = cartService.updateCart(id, updatedCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<CartWithPrice> checkout(@PathVariable Long id){
        CartWithPrice cart = cartService.checkout(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
