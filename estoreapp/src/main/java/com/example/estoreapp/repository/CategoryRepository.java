package com.example.estoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
