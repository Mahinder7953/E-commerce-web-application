package com.example.estoreapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.estoreapp.entity.CartItem;
import com.example.estoreapp.entity.User;
import com.example.estoreapp.service.CartItemService;
import com.example.estoreapp.service.OrderService;
import com.example.estoreapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/Orders")
public class UserOrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartItemService cartItemService;

    @GetMapping
    public String getOrders(Principal principal, Model model) {
        User user = userService.findUser(principal);
        model.addAttribute("Orders", orderService.getOrders(user.getId()));
        return "userOrders";
    }

    @PostMapping("/add")
    public String addItemsToOrder(Principal principal) throws Exception {
        User user = userService.findUser(principal);
        List<CartItem> cart = cartItemService.getCartItemByUser(user);
        orderService.createOrderFromCart(user,cart);
        return "redirect:/Orders";
    }
}