package com.yapily.carts.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;
    @ElementCollection
    private CartProduct[] products;
    private Boolean checked_out;

    public Cart(){
        this.products = new CartProduct[]{};
        this.checked_out = false;
    }


}
