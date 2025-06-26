package com.example.estoreapp.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    @Query(value = "Select Sum(amount) from Cart_Item Where cart_id = :id", nativeQuery = true)
    BigDecimal CartPrice(@Param("id") Long id);

    @Query(value = "Select * from Cart_Item Where cart_id = :id",nativeQuery = true)
    List<CartItem> findAllByUserId(Long id);

    @Query(value = "Select * from Cart_Item Where order_id = :orderId",nativeQuery = true)
    List<CartItem> findAllByOrderId(Long orderId);

    @Query(value = "SELECT * FROM Cart_Item WHERE cart_id = :user " ,nativeQuery = true)
    List<CartItem> findAllByUser(Long user);
}