package com.example.estoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.estoreapp.service.CategoryService;
import com.example.estoreapp.service.ProductService;
import com.example.estoreapp.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProductUserController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/Products")
    public String getProducts(Model model, @RequestParam(value = "categoryId", defaultValue = "0") Long id,
            @RequestParam(value = "query", defaultValue = "") String query) {
        model.addAttribute("Products", productService.getProducts());
        model.addAttribute("categories", categoryService.getCategories());
        if (id < 1 && query.isEmpty()) {
            model.addAttribute("Products", productService.getProducts());
        }
        else if (!query.isEmpty() && id<1) {
            model.addAttribute("Products", productService.getProductsBySearch(query));
        } else {
            model.addAttribute("Products", productService.getProductsSorted(id));
        }
        model.addAttribute("user", userService.getUser());

        return "Products";
    }
}