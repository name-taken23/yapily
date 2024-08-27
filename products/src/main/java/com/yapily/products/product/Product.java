package com.yapily.products.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name="product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
