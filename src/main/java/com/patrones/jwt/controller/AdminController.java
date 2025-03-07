package com.patrones.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class AdminController {

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Este es un endpoint solo para Admin";
    }
}