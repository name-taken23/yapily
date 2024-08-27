package com.yapily.products.product;
import java.util.List;
public interface ProductService {
    List<Product> findAll();
    void createProduct(Product product);
    Product getProductById(Long id);
    boolean deleteProductById(Long id);
    boolean findByName(String name);
}
