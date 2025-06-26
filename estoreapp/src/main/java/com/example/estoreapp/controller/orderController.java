package com.example.estoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.estoreapp.service.CartItemService;
import com.example.estoreapp.service.OrderItemService;
import com.example.estoreapp.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/Admin/Orders")
public class orderController {
    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("Orders", orderService.getAllOrders());
        return "Orders";
    }

    @GetMapping("/{OrderId}")
    public String getOrderDetails(@PathVariable Long OrderId, Model model) {
        model.addAttribute("Items", orderItemService.getOrderItems(OrderId));
        return "OrderItems";
    }

    @PostMapping("/updateStatus/{id}")
    public String postMethodName(@PathVariable Long id, @RequestParam String status) {
        orderService.updateStatus(id,status);
        return "redirect:/Admin/Orders";
    }

}