package com.example.estoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p from Product p WHERE p.category.id = :id ")
    List<Product> findAllByCategory(Long id);

    List<Product> findAllByNameContaining(String query);
}
