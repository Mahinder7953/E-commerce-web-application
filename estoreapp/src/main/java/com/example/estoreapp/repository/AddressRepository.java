package com.example.estoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estoreapp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{

}
