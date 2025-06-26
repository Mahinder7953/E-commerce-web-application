package com.example.estoreapp.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

}
// @OneToOne
// private Cart cart;
//insert into user(username,password,phone) values("123","123","232323");