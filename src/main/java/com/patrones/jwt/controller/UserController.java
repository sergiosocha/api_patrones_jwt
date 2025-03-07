package com.patrones.jwt.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public String userEndpoint() {
        return "Este es un endpoint solo para User";
    }
}