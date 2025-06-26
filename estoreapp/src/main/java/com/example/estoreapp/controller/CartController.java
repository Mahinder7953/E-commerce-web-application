package com.example.estoreapp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.estoreapp.entity.User;
import com.example.estoreapp.service.CartService;
import com.example.estoreapp.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
@RequestMapping("/Carts")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/add")
    public String addToCart(Principal principal, @RequestParam Long productId) {
        User user = userService.findUser(principal);
        cartService.addToCart(user.getId(), productId);
        return "redirect:/Products";
    }

    @GetMapping()
    public String getCartItem(Principal principal, Model model) {
        User user = userService.findUser(principal);
        model.addAttribute("carts", cartService.getCartItem(user.getId()));
        model.addAttribute("total", cartService.totalPrice(user.getId()));
        return "Carts";
    }

    @PostMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "redirect:/Carts";
    }
}