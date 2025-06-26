package com.example.estoreapp.service;

import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.estoreapp.entity.Cart;
import com.example.estoreapp.entity.User;
import com.example.estoreapp.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository UserRepository;

    public List<User> getUser() {
        return UserRepository.findAll();
    }

    public User findUser(Principal principal) {
        return UserRepository.findByUsername(principal.getName());
    }

    public Cart getCartByUser(User user) {
        return UserRepository.findAllByUserId(user.getId());
    }
}
