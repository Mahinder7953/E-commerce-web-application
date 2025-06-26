package com.example.estoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

    @Query(value = "Select * from Order_Item Where order_id = :orderId",nativeQuery = true)
    List<OrderItem> findAllByOrderId(Long orderId);

}
