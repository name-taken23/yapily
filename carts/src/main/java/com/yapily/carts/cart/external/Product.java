package com.yapily.carts.cart.external;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Product {

    private Long product_id;

    private String name;

    private Double price;

    private String added_at;

    private List<String> labels;


    public Product(){}
    public Product(Long product_id, String name, Double price, String added_at, List<String> labels){
        this.product_id = product_id;
        this.price = price;
        this.labels = labels;
        this.added_at = added_at;

    }
}
