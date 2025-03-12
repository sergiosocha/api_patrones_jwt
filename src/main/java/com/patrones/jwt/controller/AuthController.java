package com.patrones.jwt.controller;


import com.patrones.jwt.controller.dto.UserDTO;
import com.patrones.jwt.db.orm.UserORM;
import com.patrones.jwt.logica.UserService;
import com.patrones.jwt.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        if(userService.existsByUsername(userDTO.username())){
            return ResponseEntity.badRequest().body("Username ya existe");
        }

        UserORM newUser = new UserORM();
        newUser.setUsername(userDTO.username());
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));
        newUser.setRole("ROLE_"+ userDTO.role().toUpperCase());

        userService.saveUser(newUser);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
        );

        String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

}
