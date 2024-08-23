package com.yapily.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    private Integer cart_id;
    private Object product;
    private Boolean checked_out;
}
