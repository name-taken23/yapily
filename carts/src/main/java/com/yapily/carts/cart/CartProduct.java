package com.yapily.carts.cart;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(CartProductKey.class)
public class CartProduct {
    @Id
    @JsonIgnore
    Long cart_id;
    @Id
    Long product_id;
    Integer quantity;

    public CartProduct() {
    }

    public CartProduct(Long cart_id, Long product_id, Integer quantity) {
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}