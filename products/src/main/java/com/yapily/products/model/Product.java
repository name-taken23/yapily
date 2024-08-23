package com.yapily.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private Integer product_id;

    private String name;

    private Double price;

    private Date added_at;

    private String[] labels;



}
