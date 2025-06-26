package com.example.estoreapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.Product;
import com.example.estoreapp.entity.User;
import com.example.estoreapp.repository.CartItemRepository;
import com.example.estoreapp.repository.CartRepository;
import com.example.estoreapp.repository.ProductRepository;
import com.example.estoreapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public void addToCart(Long id, Long productId) {
        User user = userRepository.findById(id).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        // USER CART EXIST OR NOT
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
        
        // ITEM ALREADY EXIST OR NOT
        CartItem item = cartItemRepository.findByCartAndProduct(cart, product).orElseGet(() -> {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            return newItem;
        });
        if (item.getQuantity() == 1 && item.getAmount() == null) {
            item.setAmount(product.getPrice());
        } else {
            item.setQuantity(item.getQuantity() + 1);
            item.setAmount(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        cartItemRepository.save(item);
    }

    public List<CartItem> getCartItem(Long user) {
        return cartItemRepository.findAllByUser(user);
    }

    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    public List<CartItem> getOrders() {
        return cartItemRepository.findAll();
    }

    public BigDecimal totalPrice(Long id) {
       
        BigDecimal price= cartItemRepository.CartPrice(id);
        return price != null ? price : BigDecimal.ZERO;
    }

}