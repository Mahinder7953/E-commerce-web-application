package com.example.estoreapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.User;



@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
    Optional<Cart> findByUser(User user);
    
    // @Query(value = "SELECT * FROM Cart_Item  WHERE cart_id = :user",nativeQuery = true)
    // List<CartItem> findAllByUser(Long user);
}