package com.yapily.products.controller;

import com.yapily.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;



}
