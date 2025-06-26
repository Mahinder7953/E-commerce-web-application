package com.example.estoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.estoreapp.entity.Category;
import com.example.estoreapp.service.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/Admin/Categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public String getCategorys(Model model) {
        model.addAttribute("Categories", categoryService.getCategories());
        return "Categories";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("Category") Category entity) {
        categoryService.addCategory(entity);
        return "redirect:/Admin/Categories";
    }
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("Category") Category entity) {
        categoryService.updateCategory(id, entity);

        return "redirect:/Admin/Categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/Admin/Categories";
    }
}
