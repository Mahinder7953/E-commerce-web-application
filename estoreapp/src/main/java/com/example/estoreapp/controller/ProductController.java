package com.example.estoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.estoreapp.entity.Product;
import com.example.estoreapp.service.CategoryService;
import com.example.estoreapp.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/Admin/Products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public String getProducts(Model model, @RequestParam(value = "categoryId", defaultValue = "0") Long id,
            @RequestParam(value = "query", defaultValue = "") String query) {
        model.addAttribute("categories", categoryService.getCategories());
        if (id < 1 && query.isEmpty()) {
            model.addAttribute("Products", productService.getProducts());
        }
        else if (!query.isEmpty() && id<1) {
            model.addAttribute("Products", productService.getProductsBySearch(query));
        } else {
            model.addAttribute("Products", productService.getProductsSorted(id));
        }
        return "index";
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {
        model.addAttribute("Categories", categoryService.getCategories());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProducts(@ModelAttribute("Product") Product product, @RequestParam("Category") Long id) {
        productService.addProducts(product, id);
        return "redirect:/Admin/Products";
    }

    @GetMapping("/update/{id}")
    public String updateProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("Product", productService.updateProductPage(id));
        model.addAttribute("Categories", categoryService.getCategories());
        return "updateProduct";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product entity) {
        productService.updateProduct(id, entity);
        return "redirect:/Admin/Products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/Admin/Products";
    }
}