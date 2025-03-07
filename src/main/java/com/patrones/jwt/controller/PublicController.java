package com.patrones.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class PublicController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Este es un endpoint público";
    }
}