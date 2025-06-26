package com.example.estoreapp.service;

import org.springframework.stereotype.Service;

import com.example.estoreapp.repository.AddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
}
