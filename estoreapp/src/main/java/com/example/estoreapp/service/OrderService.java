package com.example.estoreapp.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.Order;
import com.example.estoreapp.entity.OrderItem;
import com.example.estoreapp.entity.Product;
import com.example.estoreapp.entity.User;
import com.example.estoreapp.repository.CartItemRepository;
import com.example.estoreapp.repository.CartRepository;
import com.example.estoreapp.repository.OrderItemRepository;
import com.example.estoreapp.repository.OrderRepository;
import com.example.estoreapp.repository.ProductRepository;
import com.example.estoreapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public List<Order> getOrders(Long id) {
        // return orderRepository.findAllById(id);
        User user = userRepository.findById(id).get();
        return orderRepository.findAllByUserWithItems(user);
    }

    public void createOrderFromCart(User user, List<CartItem> cart) throws Exception {
        Order order = new Order();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setStatus("PENDING");
        Cart cartid = cartRepository.findByUser(user).get();
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<CartItem> cartItemList = new ArrayList<>();
        List<OrderItem> OrderList = new ArrayList<>();
        
        for (CartItem orderItem : cart) {
            OrderItem item = new OrderItem();
            item.setAmount(orderItem.getAmount());
            item.setOrder(order);
            item.setProduct(orderItem.getProduct());
            // Product product = productRepository.findById(item.getProduct().getId()).get();
            // if (product.getQuantity()<item.getQuantity()) {
            //     throw new Exception("Item excee");
            // }
            // product.setQuantity(product.getQuantity()-item.getQuantity());
            item.setQuantity(orderItem.getQuantity());
            OrderList.add(item);
            totalAmount = totalAmount.add(item.getAmount());
        }
        // 
        order.setOrderItems(OrderList);
        order.setTotal(totalAmount);
        order.setUser(user);
        
        orderRepository.save(order);
        cartItemRepository.deleteAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElse(null);

        order.setStatus(status);
        orderRepository.save(order);
    }
}