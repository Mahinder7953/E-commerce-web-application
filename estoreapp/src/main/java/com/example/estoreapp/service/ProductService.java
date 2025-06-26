package com.example.estoreapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.estoreapp.entity.Category;
import com.example.estoreapp.entity.Product;
import com.example.estoreapp.repository.CategoryRepository;
import com.example.estoreapp.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProducts(Product product, Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        product.setCategory(category);
        productRepository.save(product);
    }

    public Product updateProductPage(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Long id, Product entity) {
        Product product = productRepository.findById(id).get();
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        product.setQuantity(entity.getQuantity());
        product.setCategory(entity.getCategory());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsSorted(Long id) {
        return productRepository.findAllByCategory(id);
    }

    public List<Product> getProductsBySearch(String query) {
        return productRepository.findAllByNameContaining(query);
    }
}