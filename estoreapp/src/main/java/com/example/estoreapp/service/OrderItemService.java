package com.example.estoreapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estoreapp.entity.OrderItem;
import com.example.estoreapp.repository.OrderItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
