package com.example.estoreapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.User;
import com.example.estoreapp.repository.CartItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemByUser(User user) {
        return cartItemRepository.findAllByUserId(user.getId());
    }

    public List<CartItem> getCart(Long orderId) {
        return cartItemRepository.findAllByOrderId(orderId);
    }
}
