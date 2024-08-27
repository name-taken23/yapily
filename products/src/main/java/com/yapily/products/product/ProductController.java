package com.yapily.products.product;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path="/products")
public class ProductController {
    private ProductService productService;
    List<String> labels = Arrays.asList("clothes", "food", "drink", "limited");
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        //rounding out the last two decimal places
        String priceString = ""+product.getPrice();
        String[] priceSplit = priceString.split("\\.");
        if(priceSplit[1].length()<3) {
            if (product.getName().length() < 200 && !product.getLabels().isEmpty() && labels.containsAll(product.getLabels()) && !productService.findByName(product.getName())) {
                LocalDateTime ldt = LocalDateTime.now();
                String added_at = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH).format(ldt);
                product.setAdded_at(added_at);
                productService.createProduct(product);
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getByProductId(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProductById(id);
        if(deleted)
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
