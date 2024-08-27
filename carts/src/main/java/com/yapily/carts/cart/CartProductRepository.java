package com.yapily.carts.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
    @Query(value = "SELECT * FROM yapily.cart_product WHERE cart_id = :id", nativeQuery = true)
    List<CartProduct> findAllByCart_Id(@Param("id") Long cartId);
    @Query(value = "SELECT * FROM yapily.cart_product WHERE cart_id = :id AND product_id = :id2", nativeQuery = true)
    CartProduct findByCartIdProductId(@Param("id") Long cartId, @Param("id2") Long productId);

}
