package com.example.estoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String name);
    @Query(value = "Select * from Cart where user_id = :id",nativeQuery = true)
    Cart findAllByUserId(Long id);
}