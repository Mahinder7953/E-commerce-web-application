package com.example.estoreapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/Admin")
    public String getAdminPage() {
        return "homeA";
    }
    @GetMapping("/")
    public String getUserPage() {
        return "homeU";
    }
    
    
}
