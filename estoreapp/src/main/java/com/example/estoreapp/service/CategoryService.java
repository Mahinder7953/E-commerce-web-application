package com.example.estoreapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estoreapp.entity.Category;
import com.example.estoreapp.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category entity) {
        categoryRepository.save(entity);
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void updateCategory(Long id, Category entity) {
        Category category = categoryRepository.findById(id).orElse(null);
        category.setName(entity.getName());

        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
