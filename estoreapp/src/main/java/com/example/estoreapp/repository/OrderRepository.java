package com.example.estoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Order;
import com.example.estoreapp.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    @Query(value = "Select * from Orders where User_Id = :id",nativeQuery = true)
    List<Order> findAllById(Long id);
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.OrderItems WHERE o.user = :user")
    List<Order> findAllByUserWithItems(@Param("user") User user);



}
